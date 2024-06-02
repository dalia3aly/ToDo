package com.todo.todoapp;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.category.Category;
import com.todo.category.CategoryRepository;
import com.todo.exceptions.CategoryNotFoundException;

// import org.modelmapper.ModelMapper;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class TodoService {

    @Autowired
    private TodoRepository repo;

    @Autowired
    private CategoryRepository catRepo;

    // @Autowired
    // private ModelMapper mapper;

    // Get all todos
    public List<Todo> findAllTodos() {
        return repo.findAll();
    }

    // Create a new todo
    public Todo createTodo(CreateTodoDTO newData) {
        Todo newTodo = new Todo();
        newTodo.setTitle(newData.getTitle());
        newTodo.setDescription(newData.getDescription());
        newTodo.setCompleted(false); // Defaulting completed to false
        newTodo.setCreatedAt(new Date());
        newTodo.setUpdatedAt(new Date());

        Optional<Category> categoryOpt = catRepo.findById(newData.getCategoryId());
        if (categoryOpt.isPresent()) {
            newTodo.setCategory(categoryOpt.get());
            return this.repo.save(newTodo);
        } else {
            throw new CategoryNotFoundException(newData.getCategoryId());
        }
    }

    // Get todo by id
    public Optional<Todo> getTodoById(Long id) {
        return repo.findById(id);
    }

    // Update a todo
    public Optional<Todo> updateTodoById(Long id, UpdateTodoDTO updatedData) {
        Optional<Todo> maybeTodo = this.findById(id);
        if (maybeTodo.isEmpty()) {
            return maybeTodo;
        }

        Todo foundTodo = maybeTodo.get();
        if (updatedData.getTitle() != null) {
            foundTodo.setTitle(updatedData.getTitle());
        }
        if (updatedData.getDescription() != null) {
            foundTodo.setDescription(updatedData.getDescription());
        }
        if (updatedData.getCompleted() != null) {
            foundTodo.setCompleted(updatedData.getCompleted());
        }
        if (updatedData.getCategoryId() != null) {
            Optional<Category> categoryOpt = catRepo.findById(updatedData.getCategoryId());
            if (categoryOpt.isPresent()) {
                foundTodo.setCategory(categoryOpt.get());
            } else {
                throw new CategoryNotFoundException(updatedData.getCategoryId());
            }
        }
        foundTodo.setUpdatedAt(new Date());
        Todo updatedTodo = this.repo.save(foundTodo);
        return Optional.of(updatedTodo);
    }

    // Delete a todo
    public boolean deleteTodoById(Long id) {
        Optional<Todo> maybeTodo = this.findById(id);
        if (maybeTodo.isEmpty()) {
            return false;
        }
        this.repo.delete(maybeTodo.get());
        return true;
    }

    // Find a todo by id
    public Optional<Todo> findById(Long id) {
        return this.repo.findById(id);
    }
}
