package com.example.SecondSeminar.controller.dto.request.member;

import com.example.SecondSeminar.domain.Part;

public record MemberProfileUpdateRequest(
        int generation,
        Part part
) {
}
