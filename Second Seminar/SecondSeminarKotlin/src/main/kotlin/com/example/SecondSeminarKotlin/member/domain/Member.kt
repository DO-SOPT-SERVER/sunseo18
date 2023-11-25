package com.example.SecondSeminarKotlin.member.domain

import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Member(
    val name: String,
    val nickname: String,
    val age: Int,
    @Embedded
    var sopt: SOPT,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    fun updateSOPT(sopt: SOPT) {
        this.sopt = sopt
    }
}
