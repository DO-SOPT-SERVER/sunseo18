package com.example.SecondSeminarKotlin.post.exception

import com.example.SecondSeminarKotlin.exception.ExceptionType
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.*

enum class PostExceptionType(
    override val httpStatus: HttpStatus,
    override val message: String
) : ExceptionType {
    NOT_FOUND_POST(NOT_FOUND, "존재하지 않는 게시글입니다")
}