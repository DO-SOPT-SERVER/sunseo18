package com.example.SecondSeminar.member.dto.request;

import com.example.SecondSeminar.member.domain.SOPT;

public record MemberCreateRequest(
        String name,
        String nickname,
        int age,
        SOPT sopt
) {
}
