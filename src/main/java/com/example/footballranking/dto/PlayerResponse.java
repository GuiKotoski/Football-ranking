package com.example.footballranking.dto;

public record PlayerResponse(
        Long id,
        String nickname,
        String realName,
        String country,
        Integer score,
        Integer wins,
        Integer losses
) {
}
