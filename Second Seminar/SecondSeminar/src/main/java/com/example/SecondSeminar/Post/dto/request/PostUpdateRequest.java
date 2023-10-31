package com.example.SecondSeminar.Post.dto.request;

import lombok.NonNull;

public record PostUpdateRequest(
        @NonNull
        String content
) {
}
