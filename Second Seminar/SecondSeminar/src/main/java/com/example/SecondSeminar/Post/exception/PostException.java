package com.example.SecondSeminar.Post.exception;

import com.example.SecondSeminar.common.exception.BaseCustomException;
import com.example.SecondSeminar.common.exception.ExceptionType;

public class PostException extends BaseCustomException {
    public PostException(ExceptionType exceptionType) {
        super(exceptionType);
    }
}
