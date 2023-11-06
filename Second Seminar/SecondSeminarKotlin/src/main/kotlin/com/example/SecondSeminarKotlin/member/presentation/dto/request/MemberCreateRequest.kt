package com.example.SecondSeminarKotlin.member.presentation.dto.request

import com.example.SecondSeminarKotlin.member.domain.SOPT

class MemberCreateRequest(
    val name: String,
    val nickname: String,
    val age: Int,
    val sopt: SOPT,
)
