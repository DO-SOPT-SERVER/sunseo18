package com.example.SeventhSeminarKotlin.Member.service

import com.example.SeventhSeminarKotlin.Member.domain.MemberRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class MemberService(
    private val memberRepository: MemberRepository,
) {

    fun getByIdOrElseThrowException(id: Long) =
        memberRepository.findByIdOrNull(id) ?: throw EntityNotFoundException("존재하지 않는 멤버입니다")
}
