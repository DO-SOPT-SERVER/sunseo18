package com.example.SecondSeminar.controller.dto.request;

import com.example.SecondSeminar.domain.Part;

public record MemberProfileUpdateRequest(
        int generation,
        Part part
) {
}
