package com.example.SecondSeminar.controller.dto.request.post;

import com.example.SecondSeminar.domain.Post;

public record PostGetResponse(
        Long id,
        String title,
        String content
//        String category
) {

    public static PostGetResponse of(Post post) {
        return new PostGetResponse(
                post.getId(),
                post.getTitle(),
                post.getContent()
        );
    }
}
