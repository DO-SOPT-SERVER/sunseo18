package com.example.SecondSeminarKotlin.presentation.dto.response

import com.example.SecondSeminarKotlin.domain.Member
import com.example.SecondSeminarKotlin.domain.SOPT

class MemberGetResponse private constructor(
    val name: String,
    val nickname: String,
    val age: Int,
    val soptInfo: SOPT,
) {

    companion object {
        fun of(member: Member): MemberGetResponse {
            return MemberGetResponse(member.name, member.nickname, member.age, member.sopt)
        }
    }
}
