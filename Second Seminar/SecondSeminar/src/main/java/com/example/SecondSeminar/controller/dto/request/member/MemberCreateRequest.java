package com.example.SecondSeminar.controller.dto.request.member;

import com.example.SecondSeminar.domain.SOPT;

public record MemberCreateRequest(
        String name,
        String nickname,
        int age,
        SOPT sopt
) {
}
