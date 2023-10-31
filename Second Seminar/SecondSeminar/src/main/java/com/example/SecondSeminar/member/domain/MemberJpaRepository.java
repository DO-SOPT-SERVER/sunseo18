package com.example.SecondSeminar.member.domain;

import com.example.SecondSeminar.member.domain.Member;
import com.example.SecondSeminar.member.exception.MemberException;
import com.example.SecondSeminar.member.exception.MemberExceptionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {

    default Member findByIdOrThrowException(Long memberId) {
        return findById(memberId).orElseThrow(() -> new MemberException(MemberExceptionType.NOT_FOUND_MEMBER));
    }
}
