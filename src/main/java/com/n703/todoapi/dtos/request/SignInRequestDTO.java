package com.n703.todoapi.dtos.request;

import jakarta.validation.constraints.Email;

public record SignInRequestDTO(
        @Email(message = "Email inválido")
        String email,
        String password
) {
}
