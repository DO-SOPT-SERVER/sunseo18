package com.example.SecondSeminarKotlin.member.application

import com.example.SecondSeminarKotlin.auth.JwtProvider
import com.example.SecondSeminarKotlin.auth.UserAuthentication
import com.example.SecondSeminarKotlin.member.domain.AuthMember
import com.example.SecondSeminarKotlin.member.domain.AuthMemberJpaRepository
import com.example.SecondSeminarKotlin.member.dto.request.AuthMemberRequest
import com.example.SecondSeminarKotlin.member.dto.response.AuthMemberSigninResponse
import com.example.SecondSeminarKotlin.member.exception.MemberException
import com.example.SecondSeminarKotlin.member.exception.MemberExceptionType
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthMemberService(
    private val authMemberJpaRepository: AuthMemberJpaRepository,
    private val jwtProvider: JwtProvider,
    private val passwordEncoder: PasswordEncoder,
) {

    @Transactional
    fun create(request: AuthMemberRequest): String {
        val authMember = AuthMember(nickname = request.nickname, password = passwordEncoder.encode(request.password))

        authMemberJpaRepository.save(authMember)

        return authMember.id.toString()
    }

    fun signIn(request: AuthMemberRequest): AuthMemberSigninResponse {
        val authMember = authMemberJpaRepository.findByNickname(nickname = request.nickname)
            .orElseThrow { MemberException(MemberExceptionType.NOT_FOUND_MEMBER) }

        if (!passwordEncoder.matches(request.password, authMember.password))
            throw MemberException(MemberExceptionType.INCORRECT_PASSWORD)

        val authentication = UserAuthentication(authMember.id, null, null)

        val jwt = jwtProvider.generateToken(authentication, 1000 * 24 * 2)
        return AuthMemberSigninResponse.of(jwt)

    }
}