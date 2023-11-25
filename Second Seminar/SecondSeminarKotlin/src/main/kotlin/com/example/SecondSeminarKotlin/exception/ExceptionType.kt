package com.example.SecondSeminarKotlin.exception

import org.springframework.http.HttpStatus

interface ExceptionType {
    val name: String
    val httpStatus: HttpStatus
    val message: String
}