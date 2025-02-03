package com.todo;

import com.todo.model.Todo;
import com.todo.repository.TodoRepository;
import com.todo.service.TodoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateTodo() {
        Todo todo = new Todo();
        todo.setTitle("Test Todo");
        todo.setDescription("Test description");

        when(todoRepository.save(any(Todo.class))).thenReturn(todo);

        Todo createdTodo = todoService.createTodo(todo);

        assertNotNull(createdTodo);
        assertEquals("Test Todo", createdTodo.getTitle());
        assertEquals("Test description", createdTodo.getDescription());
        verify(todoRepository, times(1)).save(todo); // Verify save was called once
    }

    @Test
    public void testGetAllTodos() {
        List<Todo> todos = new ArrayList<>();
        todos.add(new Todo("Test Title 1", "Test Description 1"));
        todos.add(new Todo("Test Title 2", "Test Description 2"));

        Mockito.when(todoRepository.findAll()).thenReturn(todos);

        List<Todo> result = todoService.getAllTodos();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testGetTodoById() {
        Todo todo = new Todo();
        todo.setTitle("Test Todo");
        todo.setDescription("Test description");
        todo.setId(1L);

        when(todoRepository.findById(1L)).thenReturn(Optional.of(todo));

        Optional<Todo> foundTodo = todoService.getTodoById(1L);

        assertTrue(foundTodo.isPresent());
        assertEquals("Test Todo", foundTodo.get().getTitle());
    }

    @Test
    public void testGetTodoByIdNotFound() {
        Mockito.when(todoRepository.findById(1L)).thenReturn(Optional.empty());

        todoService.getTodoById(1L);
    }

    @Test
    public void testDeleteTodo() {
        Long todoId = 1L;
        Todo todo = new Todo();
        todo.setId(todoId);
        todo.setTitle("Test Todo");
        todo.setDescription("Test description");

        when(todoRepository.findById(todoId)).thenReturn(Optional.of(todo));

        todoService.deleteTodo(todoId);

        verify(todoRepository, times(1)).delete(todo);

        when(todoRepository.findById(todoId)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> todoService.deleteTodo(todoId), "Todo not found");
    }
}