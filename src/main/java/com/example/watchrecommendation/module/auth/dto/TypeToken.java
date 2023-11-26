package com.example.watchrecommendation.module.auth.dto;

import lombok.Getter;

@Getter
public enum TypeToken {
    ACCESSTOKEN("AccessToken"),
    REFRESHTOKEN("RefreshToken");

    private final String typeToken;

    TypeToken(String typeToken) {
        this.typeToken = typeToken;
    }

    public static TypeToken getTypeToken(String typeToken) {
        for (TypeToken type : TypeToken.values()) {
            if (type.typeToken.equals(typeToken)) {
                return type;
            }
        }
        return null;
    }

}
