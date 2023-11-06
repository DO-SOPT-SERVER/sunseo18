package com.example.SecondSeminarKotlin.member.application

import com.example.SecondSeminarKotlin.member.domain.Member
import com.example.SecondSeminarKotlin.member.domain.MemberJpaRepository
import com.example.SecondSeminarKotlin.member.domain.SOPT
import com.example.SecondSeminarKotlin.member.dto.request.MemberCreateRequest
import com.example.SecondSeminarKotlin.member.dto.request.MemberProfileUpdateRequest
import com.example.SecondSeminarKotlin.member.dto.response.MemberGetResponse
import com.example.SecondSeminarKotlin.member.exception.MemberException
import com.example.SecondSeminarKotlin.member.exception.MemberExceptionType
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

    fun findMemberById(memberId: Long): Member =
        memberJpaRepository.findByIdOrNull(memberId) ?: throw MemberException(MemberExceptionType.NOT_FOUND_MEMBER)
}
