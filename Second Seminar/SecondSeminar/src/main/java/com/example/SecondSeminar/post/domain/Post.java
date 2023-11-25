package com.example.SecondSeminar.post.domain;

import com.example.SecondSeminar.category.domain.CategoryId;
import com.example.SecondSeminar.common.entity.BaseTimeEntity;
import com.example.SecondSeminar.member.domain.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "category_id", nullable = false)
    private CategoryId categoryId;

    private String imageUrl;

    @Builder
    public Post(String title, String content, Member member, CategoryId categoryId) {
        this.title = title;
        this.content = content;
        this.member = member;
        this.categoryId = categoryId;
    }

    @Builder(builderMethodName = "builderWithImageUrl")
    public Post(String title, String content, String imageUrl, Member member) {
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.member = member;
    }

    public void editContent(String content) {
        this.content = content;
    }
}
