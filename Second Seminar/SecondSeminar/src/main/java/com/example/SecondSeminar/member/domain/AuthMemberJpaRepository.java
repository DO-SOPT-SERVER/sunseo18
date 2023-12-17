package com.example.SecondSeminar.member.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthMemberJpaRepository extends JpaRepository<AuthMember, Long> {
    Optional<AuthMember> findByNickname(String nickname);
}
