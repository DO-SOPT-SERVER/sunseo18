package com.example.SecondSeminar.member.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import com.example.SecondSeminar.member.exception.MemberException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@DataJpaTest
public class MemberJpaRepositoryTest {
    @Autowired
    private MemberJpaRepository memberJpaRepository;

    @Test
    void findExistingMemberById() {
        Long id = 1L;
        Member member = memberJpaRepository.findByIdOrThrowException(id);
        assertThat(member).isNotNull();
        assertThat(member.getId()).isEqualTo(1L);
    }

    @Test
    void findNotExistingMemberById() {
        Long id = 0L;
        assertThatExceptionOfType(MemberException.class).isThrownBy(
                () -> memberJpaRepository.findByIdOrThrowException(id));
    }
}
