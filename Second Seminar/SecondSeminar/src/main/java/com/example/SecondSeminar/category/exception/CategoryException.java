package com.example.SecondSeminar.category.exception;

import com.example.SecondSeminar.common.exception.BaseCustomException;
import com.example.SecondSeminar.common.exception.ExceptionType;

public class CategoryException extends BaseCustomException {
    public CategoryException(ExceptionType exceptionType) {
        super(exceptionType);
    }
}
