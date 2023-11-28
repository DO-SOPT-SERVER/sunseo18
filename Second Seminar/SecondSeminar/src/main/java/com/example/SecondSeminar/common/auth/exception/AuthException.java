package com.example.SecondSeminar.common.auth.exception;

import com.example.SecondSeminar.common.exception.BaseCustomException;
import com.example.SecondSeminar.common.exception.ExceptionType;

public class AuthException extends BaseCustomException {

    public AuthException(ExceptionType exceptionType) {
        super(exceptionType);
    }

    @Override
    public ExceptionType getExceptionType() {
        return super.getExceptionType();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
