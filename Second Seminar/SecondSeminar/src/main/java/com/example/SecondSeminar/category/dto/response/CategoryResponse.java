package com.example.SecondSeminar.category.dto.response;

import com.example.SecondSeminar.category.domain.Category;
import com.example.SecondSeminar.category.domain.CategoryId;

public record CategoryResponse(
        Short id,
        String name
) {

    public static CategoryResponse of(Category category) {
        CategoryId categoryId = category.getCategoryId();
        return new CategoryResponse(categoryId.getCategoryId(), category.getName());
    }
}
