package com.todo.todoapp;

import jakarta.validation.constraints.NotBlank;

public class CreateTodoDTO {

    @NotBlank
    private String title;

    private Long categoryId;

    @NotBlank
    private String description;

    private Boolean completed;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "CreateTodoDTO{" +
                "title='" + title + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", description='" + description + '\'' +
                ", completed=" + completed +
                '}';
    }
}