package com.example.SecondSeminar.external.exception;

import com.example.SecondSeminar.common.exception.BaseCustomException;
import com.example.SecondSeminar.common.exception.ExceptionType;

public class S3Exception extends BaseCustomException {
    public S3Exception(ExceptionType exceptionType) {
        super(exceptionType);
    }
}
