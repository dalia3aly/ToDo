package com.todo.todoapp;

import java.util.List;
import java.util.Optional;
import com.todo.exceptions.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping()
    public ResponseEntity<Todo> createTodo(@Valid @RequestBody CreateTodoDTO data) {
        Todo createdTodo = this.todoService.createTodo(data);
        return new ResponseEntity<>(createdTodo, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<Todo>> findAllTodos() {
        List<Todo> allTodos = this.todoService.findAllTodos();
        return new ResponseEntity<>(allTodos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable Long id) throws NotFoundException {
        Optional<Todo> maybeTodo = this.todoService.getTodoById(id);
        Todo foundTodo = maybeTodo.orElseThrow(() -> new NotFoundException(Todo.class, id));
        return new ResponseEntity<>(foundTodo, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Todo> updateTodoById(@PathVariable Long id, @Valid @RequestBody UpdateTodoDTO data)
            throws NotFoundException {
        Optional<Todo> maybeTodo = this.todoService.updateTodoById(id, data);
        Todo updatedTodo = maybeTodo.orElseThrow(() -> new NotFoundException(Todo.class, id));
        return new ResponseEntity<>(updatedTodo, HttpStatus.OK);
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTodoById(@PathVariable Long id) {
        boolean deleted = this.todoService.deleteTodoById(id);
        if (deleted) {
            return new ResponseEntity<>("Todo deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Todo not found", HttpStatus.NOT_FOUND);
    }
}
