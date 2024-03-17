package com.n703.todoapi.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record ToDoRequestDTO(
        @NotBlank
        String title,
        @NotBlank
        String description
) {
}
