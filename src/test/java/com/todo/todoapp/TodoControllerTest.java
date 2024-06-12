package com.todo.todoapp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.Mockito.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.todo.category.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

@WebMvcTest(TodoController.class)
public class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoService todoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Category category;
    private Todo todo;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setId(1L);
        category.setName("Work");

        todo = new Todo();
        
        todo.setTitle("New Todo");
        todo.setDescription("This is a new todo item");
        todo.setCategory(category);
        todo.setCompleted(false);
        todo.setCreatedAt(new Date());
        todo.setUpdatedAt(new Date());
    }

    @Test
    void createTodo_ShouldReturnCreatedTodo() throws Exception {
        // Arrange
        CreateTodoDTO createTodoDTO = new CreateTodoDTO();
        createTodoDTO.setTitle("New Todo");
        createTodoDTO.setDescription("This is a new todo item");
        createTodoDTO.setCategoryId(1L);

        when(todoService.createTodo(any(CreateTodoDTO.class))).thenReturn(todo);

        // Act & Assert
        mockMvc.perform(post("/api/todos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createTodoDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("New Todo"))
                .andExpect(jsonPath("$.description").value("This is a new todo item"))
                .andExpect(jsonPath("$.category.id").value(1))
                .andExpect(jsonPath("$.completed").value(false))
                .andDo(print());
    }

    @Test
    void findAllTodos_ShouldReturnListOfTodos() throws Exception {
        // Arrange
        when(todoService.findAllTodos()).thenReturn(Arrays.asList(todo));

        // Act & Assert
        mockMvc.perform(get("/api/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("New Todo"))
                .andExpect(jsonPath("$[0].description").value("This is a new todo item"))
                .andExpect(jsonPath("$[0].category.id").value(1))
                .andExpect(jsonPath("$[0].completed").value(false))
                .andDo(print());
    }

    @Test
    void getTodoById_ShouldReturnTodo() throws Exception {
        // Arrange
        when(todoService.getTodoById(1L)).thenReturn(Optional.of(todo));

        // Act & Assert
        mockMvc.perform(get("/api/todos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("New Todo"))
                .andExpect(jsonPath("$.description").value("This is a new todo item"))
                .andExpect(jsonPath("$.category.id").value(1))
                .andExpect(jsonPath("$.completed").value(false))
                .andDo(print());
    }

    @Test
    void getTodoById_ShouldReturnNotFound() throws Exception {
        // Arrange
        when(todoService.getTodoById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        mockMvc.perform(get("/api/todos/1"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void updateTodoById_ShouldReturnUpdatedTodo() throws Exception {
        // Arrange
        UpdateTodoDTO updateTodoDTO = new UpdateTodoDTO();
        updateTodoDTO.setCompleted(true);

        todo.setCompleted(true);

        when(todoService.updateTodoById(eq(1L), any(UpdateTodoDTO.class))).thenReturn(Optional.of(todo));

        // Act & Assert
        mockMvc.perform(patch("/api/todos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateTodoDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.completed").value(true))
                .andDo(print());
    }

    @Test
    void updateTodoById_ShouldReturnNotFound() throws Exception {
        // Arrange
        UpdateTodoDTO updateTodoDTO = new UpdateTodoDTO();
        updateTodoDTO.setCompleted(true);

        when(todoService.updateTodoById(eq(1L), any(UpdateTodoDTO.class))).thenReturn(Optional.empty());

        // Act & Assert
        mockMvc.perform(patch("/api/todos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateTodoDTO)))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void deleteTodoById_ShouldReturnOk() throws Exception {
        // Arrange
        when(todoService.deleteTodoById(1L)).thenReturn(true);

        // Act & Assert
        mockMvc.perform(delete("/api/todos/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Todo deleted successfully"))
                .andDo(print());
    }

    @Test
    void deleteTodoById_ShouldReturnNotFound() throws Exception {
        // Arrange
        when(todoService.deleteTodoById(1L)).thenReturn(false);

        // Act & Assert
        mockMvc.perform(delete("/api/todos/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Todo not found"))
                .andDo(print());
    }
}
