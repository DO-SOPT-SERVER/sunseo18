package com.example.SecondSeminarKotlin.presentation.dto.request

import com.example.SecondSeminarKotlin.domain.SOPT

class MemberCreateRequest(
    val name: String,
    val nickname: String,
    val age: Int,
    val sopt: SOPT,
)
