package com.example.SecondSeminar.external.exception;

import com.example.SecondSeminar.common.exception.ExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
public enum S3ExceptionType implements ExceptionType {
    UPLOAD_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "이미지 업로드에 실패했습니다"),
    UNSUPPORTED_EXTENSION(HttpStatus.BAD_REQUEST, "지원하지 않는 확장자입니다"),
    TOO_BIG_FILE_SIZE(HttpStatus.BAD_REQUEST, "이미지 크기가 매우 큽니다");

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

