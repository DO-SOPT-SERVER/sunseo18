package com.example.SecondSeminar.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ExceptionResponse {
    private String name;
    private String message;

    public static ExceptionResponse of(String name, String message) {
        return new ExceptionResponse(name, message);
    }
}
