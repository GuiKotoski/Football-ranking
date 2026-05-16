package com.example.footballranking.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PlayerRequest(
        @NotBlank(message = "Nickname is required")
        @Size(max = 50, message = "Nickname must have at most 50 characters")
        String nickname,

        @NotBlank(message = "Real name is required")
        @Size(max = 120, message = "Real name must have at most 120 characters")
        String realName,

        @NotBlank(message = "Country is required")
        @Size(max = 80, message = "Country must have at most 80 characters")
        String country,

        @NotNull(message = "Score is required")
        @Min(value = 0, message = "Score cannot be negative")
        Integer score,

        @NotNull(message = "Wins are required")
        @Min(value = 0, message = "Wins cannot be negative")
        Integer wins,

        @NotNull(message = "Losses are required")
        @Min(value = 0, message = "Losses cannot be negative")
        Integer losses
) {
}
