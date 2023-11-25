package com.example.SecondSeminarKotlin.member.domain

import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

@Embeddable
class SOPT(
    val generation: Int,
    @Enumerated(value = EnumType.STRING)
    val part: Part,
)
