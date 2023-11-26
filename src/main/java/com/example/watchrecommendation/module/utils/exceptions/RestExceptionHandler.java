package com.example.watchrecommendation.module.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ExceptionDetails> handleConflictException(ConflictException exception) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(HttpStatus.CONFLICT.value(), exception.getMessage());
        return ResponseEntity.status(exceptionDetails.statusCode()).body(exceptionDetails);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDetails> handleNotFoundException(NotFoundException exception) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(HttpStatus.NOT_FOUND.value(), exception.getMessage());
        return ResponseEntity.status(exceptionDetails.statusCode()).body(exceptionDetails);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ExceptionDetails> handleUnauthorizedException(UnauthorizedException exception) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(HttpStatus.UNAUTHORIZED.value(), exception.getMessage());
        return ResponseEntity.status(exceptionDetails.statusCode()).body(exceptionDetails);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionDetails> handleBadRequestException(BadRequestException exception) {
        ExceptionDetails exceptionDetails = new ExceptionDetails(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
        return ResponseEntity.status(exceptionDetails.statusCode()).body(exceptionDetails);
    }

}
