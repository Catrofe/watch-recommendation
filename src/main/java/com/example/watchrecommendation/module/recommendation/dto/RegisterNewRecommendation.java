package com.example.watchrecommendation.module.recommendation.dto;

import com.example.watchrecommendation.module.recommendation.entity.TypeRecommendation;
import com.example.watchrecommendation.module.utils.exceptions.BadRequestException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RegisterNewRecommendation(
        @NotBlank @Size(max=255) String title,

        @NotBlank @Size(max=1500) String description,

        @Size(max=255) String url,

        @NotBlank @Size(max=100) String genre,

        @NotBlank @Size(max=10) String type,

        @NotNull Long streamingId){

    public RegisterNewRecommendation {
        title = title.toUpperCase();
        description = description.toUpperCase();
        genre = genre.toUpperCase();
    }

}
