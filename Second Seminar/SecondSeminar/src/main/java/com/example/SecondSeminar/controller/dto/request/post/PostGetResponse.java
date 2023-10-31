package com.example.SecondSeminar.controller.dto.request.post;

import com.example.SecondSeminar.controller.dto.response.category.CategoryResponse;
import com.example.SecondSeminar.domain.Category;
import com.example.SecondSeminar.domain.Post;

public record PostGetResponse(
        Long id,
        String title,
        String content,
        CategoryResponse category
) {

    public static PostGetResponse of(Post post, Category category) {
        return new PostGetResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                CategoryResponse.of(category)
        );
    }
}
