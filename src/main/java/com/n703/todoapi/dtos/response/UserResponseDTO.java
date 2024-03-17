package com.n703.todoapi.dtos.response;

public record UserResponseDTO(
        String name,
        String email,
        String token
) {
}
