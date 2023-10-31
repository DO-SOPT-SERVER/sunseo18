package com.example.SecondSeminar.domain;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Category {

    @EmbeddedId
    private CategoryId categoryId;

    @Column(nullable = false)
    private String name;
}
