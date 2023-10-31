package com.example.SecondSeminar.domain.repository;

import com.example.SecondSeminar.domain.Category;
import com.example.SecondSeminar.domain.CategoryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJpaRepository extends JpaRepository<Category, CategoryId> {

}
