package com.example.quiz_api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "ユーザー情報")
public record UserDto(
    @Schema(description = "ユーザー名", example = "testuser", minLength = 3, maxLength = 255)
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 255, message = "Username must be between 3 and 255 characters")
    String username,
    
    @Schema(description = "パスワード", example = "password123", minLength = 6, maxLength = 255)
    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 255, message = "Password must be between 6 and 255 characters")
    String password
) {}