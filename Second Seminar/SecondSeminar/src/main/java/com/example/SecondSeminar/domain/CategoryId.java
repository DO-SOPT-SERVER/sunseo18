package com.example.SecondSeminar.domain;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryId implements Serializable {
    private Short categoryId;

    public CategoryId(Short id) {
        this.categoryId = id;
    }
}
