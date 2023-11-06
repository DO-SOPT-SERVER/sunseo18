package com.example.SecondSeminar.category.application;

import com.example.SecondSeminar.category.domain.Category;
import com.example.SecondSeminar.category.domain.CategoryId;
import com.example.SecondSeminar.category.domain.CategoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CategoryService {

    private final CategoryJpaRepository categoryJpaRepository;

    public Category getByCategoryId(CategoryId categoryId) {
        return categoryJpaRepository.findByIdOrThrowException(categoryId);
    }

}
