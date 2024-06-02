package com.todo.exceptions;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(Long long1) {
        super(long1 + " not found");
    }
}
