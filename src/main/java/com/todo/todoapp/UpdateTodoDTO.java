package com.todo.todoapp;

import jakarta.validation.constraints.Size;

public class UpdateTodoDTO {

    private Boolean completed;


    @Size(min = 1, message = "Title cannot be empty")
    private String title;

    private Long categoryId;

    @Size(min = 1, message = "Description cannot be empty")
    private String description;

    public Boolean getCompleted() {
        return completed;
    }

    public String getTitle() {
        return title;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "UpdateTodoDTO{" +
                "completed='" + completed + '\'' +
                ", title='" + title + '\'' +
                ", categoryId=" + categoryId +
                ", description='" + description + '\'' +
                '}';
    }
}
