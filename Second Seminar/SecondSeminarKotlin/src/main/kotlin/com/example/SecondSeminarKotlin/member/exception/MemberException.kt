package com.example.SecondSeminarKotlin.member.exception

import com.example.SecondSeminarKotlin.exception.BaseCustomException
import com.example.SecondSeminarKotlin.exception.ExceptionType

class MemberException(exception: ExceptionType) : BaseCustomException(exception)