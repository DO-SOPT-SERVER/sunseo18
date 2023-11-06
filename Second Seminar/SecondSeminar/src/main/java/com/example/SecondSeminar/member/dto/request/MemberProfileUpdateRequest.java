package com.example.SecondSeminar.member.dto.request;

import com.example.SecondSeminar.member.domain.Part;

public record MemberProfileUpdateRequest(
        int generation,
        Part part
) {
}
