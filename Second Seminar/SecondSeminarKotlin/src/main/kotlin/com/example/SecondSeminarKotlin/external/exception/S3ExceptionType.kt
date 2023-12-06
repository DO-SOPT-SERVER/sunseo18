package com.example.SecondSeminarKotlin.external.exception

import com.example.SecondSeminarKotlin.exception.ExceptionType
import org.springframework.http.HttpStatus

enum class S3ExceptionType(
        override val httpStatus: HttpStatus, override val message: String
) : ExceptionType {
    UPLOAD_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "이미지 업로드에 실패했습니다"),
    UNSUPPORTED_EXTENSION(HttpStatus.BAD_REQUEST, "지원하지 않는 확장자입니다"),
    TOO_BIG_FILE_SIZE(HttpStatus.BAD_REQUEST, "이미지 크기가 매우 큽니다")

}