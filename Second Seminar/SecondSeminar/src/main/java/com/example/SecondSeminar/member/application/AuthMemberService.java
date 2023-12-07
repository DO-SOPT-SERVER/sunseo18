package com.example.SecondSeminar.member.application;

import com.example.SecondSeminar.common.auth.JwtProvider;
import com.example.SecondSeminar.common.auth.UserAuthentication;
import com.example.SecondSeminar.common.exception.BaseCustomException;
import com.example.SecondSeminar.member.domain.AuthMember;
import com.example.SecondSeminar.member.domain.AuthMemberJpaRepository;
import com.example.SecondSeminar.member.dto.request.AuthMemberRequest;
import com.example.SecondSeminar.member.dto.response.MemberSignInResponse;
import com.example.SecondSeminar.member.exception.MemberExceptionType;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthMemberService {

    private final AuthMemberJpaRepository authMemberJpaRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Transactional
    public String create(AuthMemberRequest request) {
        AuthMember authMember = AuthMember.builder()
                .nickname(request.nickname())
                .password(passwordEncoder.encode(request.password()))
                .build();
        authMemberJpaRepository.save(authMember);

        return authMember.getId().toString();
    }

    public MemberSignInResponse signIn(AuthMemberRequest request) {
        AuthMember authMember = authMemberJpaRepository.findByNickname(request.nickname())
                .orElseThrow(() -> new BaseCustomException(MemberExceptionType.NOT_FOUND_MEMBER));

        if (!passwordEncoder.matches(request.password(), authMember.getPassword())) {
            throw new BaseCustomException(MemberExceptionType.INCORRECT_PASSWORD);
        }

        Authentication authentication = new UserAuthentication(authMember.getId(), null, null);

        // 이거도 application.yaml로 해야됨
        String jwtToken = jwtProvider.generateToken(authentication, 1000L * 24);
        return MemberSignInResponse.of(jwtToken);
    }

}
