package com.example.watchrecommendation.module.streaming.dto;

import lombok.NoArgsConstructor;

public record SaveStreaming(String name, String url) {

    public SaveStreaming(String name, String url) {
        this.name = name.toUpperCase();
        this.url = url;
    }

}
