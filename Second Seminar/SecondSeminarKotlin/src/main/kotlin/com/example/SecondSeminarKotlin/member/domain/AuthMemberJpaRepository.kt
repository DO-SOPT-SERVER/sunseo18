package com.example.SecondSeminarKotlin.member.domain

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AuthMemberJpaRepository : JpaRepository<AuthMember, Long> {
    fun findByNickname(nickname: String): Optional<AuthMember>
}