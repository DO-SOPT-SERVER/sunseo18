package com.example.SecondSeminarKotlin.post.exception

import com.example.SecondSeminarKotlin.exception.BaseCustomException
import com.example.SecondSeminarKotlin.exception.ExceptionType

class PostException(exception: ExceptionType) : BaseCustomException(exception)