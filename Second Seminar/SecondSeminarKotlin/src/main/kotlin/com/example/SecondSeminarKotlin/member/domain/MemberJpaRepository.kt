package com.example.SecondSeminarKotlin.member.domain

import org.springframework.data.jpa.repository.JpaRepository

interface MemberJpaRepository : JpaRepository<Member, Long>
