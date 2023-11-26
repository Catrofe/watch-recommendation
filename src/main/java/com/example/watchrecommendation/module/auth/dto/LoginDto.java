package com.example.watchrecommendation.module.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginDto(
        @NotBlank String login,
        @NotBlank @Size(min=8, max = 255) String password) {

}
