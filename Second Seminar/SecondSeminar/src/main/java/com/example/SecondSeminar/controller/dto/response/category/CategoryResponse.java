package com.example.SecondSeminar.controller.dto.response.category;

import com.example.SecondSeminar.domain.Category;
import com.example.SecondSeminar.domain.CategoryId;

public record CategoryResponse(
        Short id,
        String name
) {

    public static CategoryResponse of(Category category) {
        CategoryId categoryId = category.getCategoryId();
        return new CategoryResponse(categoryId.getCategoryId(), category.getName());
    }
}
