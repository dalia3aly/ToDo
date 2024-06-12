package com.todo.todoapp;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.Optional;

import com.todo.category.Category;
import com.todo.category.CategoryRepository;
import com.todo.exceptions.CategoryNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private TodoService todoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createTodo_ShouldCreateTodo() {
        // Arrange
        CreateTodoDTO createTodoDTO = new CreateTodoDTO();
        createTodoDTO.setTitle("New Todo");
        createTodoDTO.setDescription("This is a new todo item");
        createTodoDTO.setCategoryId(1L);

        Category category = new Category();
        // category.setId(1L);
        category.setName("Work");

        when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
        when(todoRepository.save(any(Todo.class))).thenAnswer(i -> i.getArgument(0));

        // Act
        Todo createdTodo = todoService.createTodo(createTodoDTO);

        // Assert
        assertThat(createdTodo).isNotNull();
        assertThat(createdTodo.getTitle()).isEqualTo("New Todo");
        assertThat(createdTodo.getDescription()).isEqualTo("This is a new todo item");
        assertThat(createdTodo.getCategory()).isEqualTo(category);
        assertThat(createdTodo.getCompleted()).isFalse();
        assertThat(createdTodo.getCreatedAt()).isBeforeOrEqualTo(new Date());
        assertThat(createdTodo.getUpdatedAt()).isBeforeOrEqualTo(new Date());
    }

    @Test
    void createTodo_ShouldThrowCategoryNotFoundException() {
        // Arrange
        CreateTodoDTO createTodoDTO = new CreateTodoDTO();
        createTodoDTO.setTitle("New Todo");
        createTodoDTO.setDescription("This is a new todo item");
        createTodoDTO.setCategoryId(1L);

        when(categoryRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(CategoryNotFoundException.class, () -> {
            todoService.createTodo(createTodoDTO);
        });
    }
}
