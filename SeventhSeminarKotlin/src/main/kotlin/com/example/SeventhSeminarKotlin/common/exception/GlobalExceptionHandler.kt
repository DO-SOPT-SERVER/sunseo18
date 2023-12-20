package com.example.SeventhSeminarKotlin.common.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(
        MethodArgumentNotValidException::class,
    )
    fun handleMethodArgumentNotValidException(error: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        return ResponseEntity.badRequest()
            .body(ErrorResponse(error.message))
    }
}
