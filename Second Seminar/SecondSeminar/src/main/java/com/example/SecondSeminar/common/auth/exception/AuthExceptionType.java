package com.example.SecondSeminar.common.auth.exception;

import com.example.SecondSeminar.common.exception.ExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum AuthExceptionType implements ExceptionType {
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다");

    private final HttpStatus httpStatus;
    private final String message;

    @Override
    public HttpStatus httpStatus() {
        return httpStatus;
    }

    @Override
    public String message() {
        return message;
    }

}
