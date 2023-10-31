package com.example.SecondSeminarKotlin.application

import com.example.SecondSeminarKotlin.domain.Member
import com.example.SecondSeminarKotlin.domain.SOPT
import com.example.SecondSeminarKotlin.domain.repository.MemberJpaRepository
import com.example.SecondSeminarKotlin.presentation.dto.request.MemberCreateRequest
import com.example.SecondSeminarKotlin.presentation.dto.request.MemberProfileUpdateRequest
import com.example.SecondSeminarKotlin.presentation.dto.response.MemberGetResponse
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class MemberService(
    private val memberJpaRepository: MemberJpaRepository,
) {

    fun getMemberByIdV1(memberId: Long): MemberGetResponse =
        MemberGetResponse.of(memberJpaRepository.findById(memberId).get())

    fun getMemberByIdV2(memberId: Long): MemberGetResponse =
        MemberGetResponse.of(findMemberById(memberId))

    fun getMembers(): List<MemberGetResponse> =
        memberJpaRepository.findAll().asSequence().map(MemberGetResponse::of).toList()

    @Transactional
    fun create(request: MemberCreateRequest): String =
        memberJpaRepository.save(
            Member(
                request.name,
                request.nickname,
                request.age,
                request.sopt,
            ),
        ).id.toString()

    @Transactional
    fun updateSOPT(memberId: Long, request: MemberProfileUpdateRequest) {
        val member: Member = findMemberById(memberId)
        member.updateSOPT(SOPT(request.generation, request.part))
    }

    @Transactional
    fun deleteMember(memberId: Long) =
        memberJpaRepository.delete(findMemberById(memberId))

    private fun findMemberById(memberId: Long): Member =
        memberJpaRepository.findByIdOrNull(memberId) ?: throw EntityNotFoundException("존재하지 않는 회원입니다")
}
