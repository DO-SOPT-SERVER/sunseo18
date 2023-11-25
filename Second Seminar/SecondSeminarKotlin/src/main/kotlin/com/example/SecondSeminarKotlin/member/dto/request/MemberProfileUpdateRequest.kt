package com.example.SecondSeminarKotlin.member.dto.request

import com.example.SecondSeminarKotlin.member.domain.Part


class MemberProfileUpdateRequest(
    val generation: Int,
    val part: Part,
)
