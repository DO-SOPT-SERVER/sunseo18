package com.example.SecondSeminar.controller.dto.request.post;

import lombok.NonNull;

public record PostUpdateRequest(
        @NonNull
        String content
) {
}
