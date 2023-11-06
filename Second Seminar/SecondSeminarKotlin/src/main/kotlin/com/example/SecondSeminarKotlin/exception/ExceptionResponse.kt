package com.example.SecondSeminarKotlin.exception

class ExceptionResponse(
    val name: String,
    val message: String
) {
    companion object {
        fun of(name: String, message: String): ExceptionResponse {
            return ExceptionResponse(name, message)
        }
    }
}