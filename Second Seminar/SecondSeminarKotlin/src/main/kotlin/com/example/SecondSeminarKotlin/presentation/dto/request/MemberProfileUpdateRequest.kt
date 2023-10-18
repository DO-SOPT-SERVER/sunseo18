package com.example.SecondSeminarKotlin.presentation.dto.request

import com.example.SecondSeminarKotlin.domain.Part


class MemberProfileUpdateRequest(
    val generation: Int,
    val part: Part,
)
