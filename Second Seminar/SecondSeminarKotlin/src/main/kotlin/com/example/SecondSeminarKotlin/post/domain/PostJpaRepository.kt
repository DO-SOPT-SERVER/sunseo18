package com.example.SecondSeminarKotlin.post.domain

import com.example.SecondSeminarKotlin.member.domain.Member
import org.springframework.data.jpa.repository.JpaRepository

interface PostJpaRepository : JpaRepository<Post, Long> {

    fun findAllByMemberId(memberId: Long): List<Post>

    fun findAllByMember(member: Member): List<Post>

}