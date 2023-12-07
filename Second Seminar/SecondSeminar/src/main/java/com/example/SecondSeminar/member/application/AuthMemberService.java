package com.example.SecondSeminar.member.application;

import com.example.SecondSeminar.common.exception.BaseCustomException;
import com.example.SecondSeminar.member.domain.AuthMember;
import com.example.SecondSeminar.member.domain.AuthMemberJpaRepository;
import com.example.SecondSeminar.member.dto.request.AuthMemberRequest;
import com.example.SecondSeminar.member.exception.MemberExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthMemberService {

    private final AuthMemberJpaRepository authMemberJpaRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public String create(AuthMemberRequest request) {
        AuthMember authMember = AuthMember.builder()
                .nickname(request.nickname())
                .password(passwordEncoder.encode(request.password()))
                .build();
        authMemberJpaRepository.save(authMember);

        return authMember.getId().toString();
    }

    public void signIn(AuthMemberRequest request) {
        AuthMember serviceMember = authMemberJpaRepository.findByNickname(request.nickname())
                .orElseThrow(() -> new BaseCustomException(MemberExceptionType.NOT_FOUND_MEMBER));
        if (!passwordEncoder.matches(request.password(), serviceMember.getPassword())) {
            throw new BaseCustomException(MemberExceptionType.INCORRECT_PASSWORD);
        }
    }

}
