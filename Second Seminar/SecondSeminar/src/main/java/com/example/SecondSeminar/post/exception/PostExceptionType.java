package com.example.SecondSeminar.post.exception;

import com.example.SecondSeminar.common.exception.ExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum PostExceptionType implements ExceptionType {
    NOT_FOUND_POST(HttpStatus.NOT_FOUND, "존재하지 않는 게시글입니다.");

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
