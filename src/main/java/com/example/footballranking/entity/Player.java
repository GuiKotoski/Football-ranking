package com.example.footballranking.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "players")

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nickname is required")
    @Size(max = 50, message = "Nickname must have at most 50 characters")
    @Column(nullable = false, unique = true, length = 50)
    private String nickname;

    @NotBlank(message = "Real name is required")
    @Size(max = 120, message = "Real name must have at most 120 characters")
    @Column(nullable = false, length = 120)
    private String realName;

    @NotBlank(message = "Country is required")
    @Size(max = 80, message = "Country must have at most 80 characters")
    @Column(nullable = false, length = 80)
    private String country;

    @NotNull(message = "Score is required")
    @Min(value = 0, message = "Score cannot be negative")
    @Column(nullable = false)
    private Integer score;

    @NotNull(message = "Wins are required")
    @Min(value = 0, message = "Wins cannot be negative")
    @Column(nullable = false)
    private Integer wins;

    @NotNull(message = "Losses are required")
    @Min(value = 0, message = "Losses cannot be negative")
    @Column(nullable = false)
    private Integer losses;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
