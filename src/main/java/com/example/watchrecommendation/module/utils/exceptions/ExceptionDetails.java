package com.example.watchrecommendation.module.utils.exceptions;

import java.time.LocalDateTime;

public record ExceptionDetails(Integer statusCode, String message, LocalDateTime timestamp) {

    public ExceptionDetails(Integer statusCode, String message) {
        this(statusCode, message, LocalDateTime.now());
    }

}
