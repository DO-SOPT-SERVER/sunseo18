package com.example.SecondSeminar.service;

import com.example.SecondSeminar.controller.dto.request.post.PostCreateRequest;
import com.example.SecondSeminar.controller.dto.request.post.PostGetResponse;
import com.example.SecondSeminar.controller.dto.request.post.PostUpdateRequest;
import com.example.SecondSeminar.domain.Category;
import com.example.SecondSeminar.domain.CategoryId;
import com.example.SecondSeminar.domain.Member;
import com.example.SecondSeminar.domain.Post;
import com.example.SecondSeminar.domain.repository.MemberJpaRepository;
import com.example.SecondSeminar.domain.repository.PostJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PostService {

    private final PostJpaRepository postJpaRepository;
    private final MemberJpaRepository memberJpaRepository;
    private final CategoryService categoryService;


    @Transactional
    public String create(PostCreateRequest request, Long memberId) {
        Member member = memberJpaRepository.findByIdOrThrowException(memberId);

        CategoryId categoryId = new CategoryId(request.categoryId());
        categoryService.getByCategoryId(new CategoryId(request.categoryId()));

        Post post = postJpaRepository.save(
                Post.builder()
                        .member(member)
                        .title(request.title())
                        .content(request.content())
                        .categoryId(categoryId).build());
        return post.getId().toString();
    }

    public List<PostGetResponse> getPostsByMemberId(Long memberId) {
        Member member = memberJpaRepository.findByIdOrThrowException(memberId);
        return postJpaRepository.
                findAllByMemberId(member.getId()).
                stream().
                map(post -> PostGetResponse.of(post, getCategoryByPost(post))).
                toList();
    }

    public PostGetResponse getPostById(Long postId) {
        Post post = postJpaRepository.findByIdOrThrowException(postId);
        return PostGetResponse.of(post, getCategoryByPost(post));
    }

    @Transactional
    public void editContent(Long postId, PostUpdateRequest request) {
        Post post = postJpaRepository.findByIdOrThrowException(postId);
        post.editContent(request.content());
    }

    @Transactional
    public void deleteById(Long postId) {
        postJpaRepository.findByIdOrThrowException(postId);
        postJpaRepository.deleteById(postId);
    }

    private Category getCategoryByPost(Post post) {
        return categoryService.getByCategoryId(post.getCategoryId());
    }
}