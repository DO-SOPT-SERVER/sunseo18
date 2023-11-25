package com.example.SecondSeminarKotlin.exception

import org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

private const val SERVER_ERROR_MESSAGE = "서버 오류가 발생하였습니다"

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(BaseCustomException::class)
    protected fun handleBaseException(e: BaseCustomException): ResponseEntity<ExceptionResponse> {
        return ResponseEntity.status(e.getHttpStatus()).body(e.toErrorResponse())
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(RuntimeException::class)
    protected fun handleInternalServerError(e: RuntimeException): ResponseEntity<ExceptionResponse> {
        val httpStatus = INTERNAL_SERVER_ERROR
        return ResponseEntity.status(httpStatus)
            .body(ExceptionResponse.of(httpStatus.name, SERVER_ERROR_MESSAGE))
    }
}