package com.example.watchrecommendation.module.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegister(
        @NotBlank String name,
        @NotBlank String cpf,
        @NotBlank String email,
        @NotBlank @Size(min=8, max = 255) String password,
        @NotBlank @Size(min=10, max=15) String phone) {
}
