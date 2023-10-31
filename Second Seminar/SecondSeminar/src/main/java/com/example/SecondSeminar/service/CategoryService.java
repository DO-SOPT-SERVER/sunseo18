package com.example.SecondSeminar.service;

import com.example.SecondSeminar.controller.dto.response.category.CategoryResponse;
import com.example.SecondSeminar.domain.Category;
import com.example.SecondSeminar.domain.CategoryId;
import com.example.SecondSeminar.domain.repository.CategoryJpaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CategoryService {

    private final CategoryJpaRepository categoryJpaRepository;

    public Category getByCategoryId(CategoryId categoryId) {
        return categoryJpaRepository.findById(categoryId).orElseThrow(
                () -> new EntityNotFoundException("존재하지 않는 카테고리입니다")
        );
    }

}
