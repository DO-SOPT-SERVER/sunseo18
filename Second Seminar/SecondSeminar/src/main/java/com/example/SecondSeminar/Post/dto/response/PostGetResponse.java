package com.example.SecondSeminar.Post.dto.response;

import com.example.SecondSeminar.category.dto.response.CategoryResponse;
import com.example.SecondSeminar.category.domain.Category;
import com.example.SecondSeminar.Post.domain.Post;

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
