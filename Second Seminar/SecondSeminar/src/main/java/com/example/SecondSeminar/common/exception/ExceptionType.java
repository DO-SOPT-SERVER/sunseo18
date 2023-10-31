package com.example.SecondSeminar.common.exception;

import org.springframework.http.HttpStatus;

public interface ExceptionType {
    String name();

    HttpStatus httpStatus();

    String message();

}
