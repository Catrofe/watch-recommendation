package com.example.watchrecommendation.module.recommendation.entity;

import lombok.Getter;

@Getter
public enum TypeRecommendation {
    MOVIE("MOVIE"),
    TV_SHOW("TVSHOW");

    private final String type;

    TypeRecommendation(String type) {
        this.type = type;
    }

    public static TypeRecommendation getTypeRecommendation(String type) {
        for (TypeRecommendation typeRecommendation : TypeRecommendation.values()) {
            if (typeRecommendation.type.equals(type)) {
                return typeRecommendation;
            }
        }
        return null;
    }

}
