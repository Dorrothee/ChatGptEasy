package com.todo;

import com.todo.model.Todo;
import com.todo.repository.TodoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    private Todo todo;

    @BeforeEach
    public void setUp() {
        todo = new Todo();
        todo.setTitle("Test Todo");
        todo.setDescription("Test description");
    }

    @Test
    public void testSaveTodo() {
        Todo savedTodo = todoRepository.save(todo);
        assertNotNull(savedTodo);
        assertEquals("Test Todo", savedTodo.getTitle());
    }

    @Test
    public void testFindById() {
        Todo savedTodo = todoRepository.save(todo);
        Optional<Todo> foundTodo = todoRepository.findById(savedTodo.getId());

        assertTrue(foundTodo.isPresent());
        assertEquals(savedTodo.getTitle(), foundTodo.get().getTitle());
    }

    @Test
    public void testDeleteById() {
        Todo savedTodo = todoRepository.save(todo);
        todoRepository.deleteById(savedTodo.getId());

        Optional<Todo> foundTodo = todoRepository.findById(savedTodo.getId());
        assertFalse(foundTodo.isPresent());
    }
}

