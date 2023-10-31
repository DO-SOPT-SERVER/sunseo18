package com.example.SecondSeminar.Post.dto.request;

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
