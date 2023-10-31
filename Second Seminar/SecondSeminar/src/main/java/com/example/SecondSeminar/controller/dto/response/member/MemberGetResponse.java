package com.example.SecondSeminar.controller.dto.response.member;

import com.example.SecondSeminar.domain.Member;
import com.example.SecondSeminar.domain.SOPT;

public record MemberGetResponse(
        String name,
        String nickname,
        int age,
        SOPT soptInfo
) {
    public static MemberGetResponse of(Member member) {
        return new MemberGetResponse(member.getName(), member.getNickname(), member.getAge(), member.getSopt());
    }
}
