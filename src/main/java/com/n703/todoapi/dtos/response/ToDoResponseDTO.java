package com.n703.todoapi.dtos.response;

import com.n703.todoapi.models.ToDo;

public record ToDoResponseDTO(
        Long id,
        String title,
        String description,
        boolean isComplete
) {

    public ToDoResponseDTO(ToDo todo) {
        this(
                todo.getId(),
                todo.getTitle(),
                todo.getDescription(),
                todo.isComplete()
        );
    }
}
