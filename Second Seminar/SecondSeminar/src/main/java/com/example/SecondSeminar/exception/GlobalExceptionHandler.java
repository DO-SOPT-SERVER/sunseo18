package com.example.SecondSeminar.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Void> handleNotFoundException(final NotFoundException e) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(IllegalAccessException.class)
    public ResponseEntity<Void> handleIllegalArgumentException(final IllegalAccessException e) {
        return ResponseEntity.badRequest().build();
    }
}
