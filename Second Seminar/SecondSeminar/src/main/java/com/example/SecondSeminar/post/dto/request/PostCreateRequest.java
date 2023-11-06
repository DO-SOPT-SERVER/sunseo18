package com.example.SecondSeminar.post.dto.request;

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
