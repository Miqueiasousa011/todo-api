package com.n703.todoapi.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record SignUpRequestDTO(
        @NotBlank
        String name,
        @Email
        String email,
        @NotBlank
        @Min(value = 6, message = "A senha deve ter no mínimo 6 caracteres")
        String password
) {
}
