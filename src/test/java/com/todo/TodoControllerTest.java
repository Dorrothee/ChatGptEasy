package com.todo;

import com.todo.controller.TodoController;
import com.todo.model.Todo;
import com.todo.service.TodoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TodoController.class)
public class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoService todoService;

    // Test the POST /api/todos endpoint
    @Test
    public void testCreateTodo() throws Exception {
        Todo todo = new Todo("Test Title", "Test Description");

        Mockito.when(todoService.createTodo(Mockito.any(Todo.class))).thenReturn(todo);

        mockMvc.perform(post("/api/todos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"title\": \"Test Title\", \"description\": \"Test Description\" }"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Test Title"))
                .andExpect(jsonPath("$.description").value("Test Description"));
    }

    // Test the GET /api/todos/{id} endpoint
    @Test
    public void testGetTodoById() throws Exception {
        Todo todo = new Todo("Test Title", "Test Description");

        Mockito.when(todoService.getTodoById(1L)).thenReturn(Optional.of(todo));

        mockMvc.perform(get("/api/todos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Title"))
                .andExpect(jsonPath("$.description").value("Test Description"));
    }

    // Test the GET /api/todos endpoint
    @Test
    public void testGetAllTodos() throws Exception {
        List<Todo> todos = Arrays.asList(
                new Todo("Test Title 1", "Test Description 1"),
                new Todo("Test Title 2", "Test Description 2")
        );

        Mockito.when(todoService.getAllTodos()).thenReturn(todos);

        mockMvc.perform(get("/api/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
}
