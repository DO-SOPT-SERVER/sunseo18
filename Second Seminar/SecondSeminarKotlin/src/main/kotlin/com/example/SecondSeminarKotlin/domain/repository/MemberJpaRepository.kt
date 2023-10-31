package com.example.SecondSeminarKotlin.domain.repository

import com.example.SecondSeminarKotlin.domain.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberJpaRepository : JpaRepository<Member, Long>
