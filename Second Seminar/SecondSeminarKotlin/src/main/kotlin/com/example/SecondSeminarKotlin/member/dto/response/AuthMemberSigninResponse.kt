package com.example.SecondSeminarKotlin.member.dto.response

class AuthMemberSigninResponse private constructor(
    val jwtToken: String
) {

    companion object {
        fun of(jwtToken: String): AuthMemberSigninResponse {
            return AuthMemberSigninResponse(jwtToken)
        }
    }
}