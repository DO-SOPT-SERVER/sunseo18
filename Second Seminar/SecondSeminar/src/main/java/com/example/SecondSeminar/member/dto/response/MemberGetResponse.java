package com.example.SecondSeminar.member.dto.response;

import com.example.SecondSeminar.member.domain.Member;
import com.example.SecondSeminar.member.domain.SOPT;

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
