package com.example.SecondSeminarKotlin.exception

import org.springframework.http.HttpStatus

open class BaseCustomException(
    private val exceptionType: ExceptionType
) : RuntimeException(exceptionType.message) {

    fun getHttpStatus(): HttpStatus {
        return exceptionType.httpStatus
    }

    fun toErrorResponse(): ExceptionResponse {
        return ExceptionResponse.of(exceptionType.name, exceptionType.message)
    }
}