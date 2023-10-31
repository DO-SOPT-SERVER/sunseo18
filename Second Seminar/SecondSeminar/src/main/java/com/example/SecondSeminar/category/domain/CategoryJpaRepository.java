package com.example.SecondSeminar.category.domain;

import com.example.SecondSeminar.category.exception.CategoryException;
import com.example.SecondSeminar.category.exception.CategoryExceptionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJpaRepository extends JpaRepository<Category, CategoryId> {

    default Category findByIdOrThrowException(CategoryId categoryId) {
        return findById(categoryId).orElseThrow(() -> new CategoryException(CategoryExceptionType.NOT_FOUND_CATEGORY));
    }

}
