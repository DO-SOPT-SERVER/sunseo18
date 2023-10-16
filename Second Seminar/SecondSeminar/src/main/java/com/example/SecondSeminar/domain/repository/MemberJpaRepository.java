package com.example.SecondSeminar.domain.repository;

import com.example.SecondSeminar.domain.Member;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<Member, Long> {

    default Member findByIdOrThrowException(Long memberId) {
        return findById(memberId).orElseThrow(() -> new EntityNotFoundException("존재하지 않는 회원입니다."));
    }
}
