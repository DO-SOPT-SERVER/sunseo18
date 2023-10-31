package com.example.SecondSeminar.member.exception;

import com.example.SecondSeminar.common.exception.BaseCustomException;
import com.example.SecondSeminar.common.exception.ExceptionType;

public class MemberException extends BaseCustomException {
    public MemberException(ExceptionType exceptionType) {
        super(exceptionType);
    }
}
