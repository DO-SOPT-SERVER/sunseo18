package com.example.SecondSeminarKotlin.member.exception

import com.example.SecondSeminarKotlin.exception.ExceptionType
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.*

enum class MemberExceptionType(
    override val httpStatus: HttpStatus,
    override val message: String
): ExceptionType {
    NOT_FOUND_MEMBER(NOT_FOUND, "존재하지 않는 멤버입니다.")
}