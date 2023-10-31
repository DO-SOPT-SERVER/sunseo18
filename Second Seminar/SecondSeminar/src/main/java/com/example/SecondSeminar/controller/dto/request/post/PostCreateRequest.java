package com.example.SecondSeminar.controller.dto.request.post;

import lombok.NonNull;

public record PostCreateRequest(
        @NonNull
        String title,
        @NonNull
        String content,
        @NonNull
        Short categoryId
) {
}
