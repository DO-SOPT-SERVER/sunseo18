package com.example.SecondSeminar.controller;

import com.example.SecondSeminar.controller.dto.request.post.PostCreateRequest;
import com.example.SecondSeminar.controller.dto.request.post.PostGetResponse;
import com.example.SecondSeminar.controller.dto.request.post.PostUpdateRequest;
import com.example.SecondSeminar.service.PostService;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/posts")
@RestController
public class PostController {

    private static final String CUSTOM_AUTH_ID = "X-AUTH-ID";
    private final PostService postService;

    @PostMapping
    public ResponseEntity<Void> createPost(@RequestHeader(CUSTOM_AUTH_ID) Long memberId,
                                           @RequestBody PostCreateRequest request) {
        URI location = URI.create("/api/post/" + postService.create(request, memberId));
        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public ResponseEntity<List<PostGetResponse>> getPostsByMemberId(@RequestHeader(CUSTOM_AUTH_ID) Long memberId) {
        return ResponseEntity.ok(postService.getPostsByMemberId(memberId));
    }

    @GetMapping("{postId}")
    public ResponseEntity<PostGetResponse> getPostById(@PathVariable Long postId) {
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    @PatchMapping("{postId}")
    public ResponseEntity<Void> updatePost(@PathVariable Long postId, @RequestBody PostUpdateRequest request) {
        postService.editContent(postId, request);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{postId}")
    public ResponseEntity<Void> deletePostById(@PathVariable Long postId) {
        postService.deleteById(postId);
        return ResponseEntity.noContent().build();
    }

}
