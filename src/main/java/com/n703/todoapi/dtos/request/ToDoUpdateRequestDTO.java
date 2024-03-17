package com.n703.todoapi.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record ToDoUpdateRequestDTO(
        @NotBlank
        String title,
        @NotBlank
        String description,
        boolean isComplete
) {
}
