package com.example.SecondSeminar.post.dto.request;

import lombok.NonNull;

public record PostUpdateRequest(
        @NonNull
        String content
) {
}
