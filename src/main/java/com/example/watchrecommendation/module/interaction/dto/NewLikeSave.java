package com.example.watchrecommendation.module.interaction.dto;

import jakarta.validation.constraints.NotNull;

public record NewLikeSave(
        @NotNull Long recommendationId,
        @NotNull Boolean like) {
}
