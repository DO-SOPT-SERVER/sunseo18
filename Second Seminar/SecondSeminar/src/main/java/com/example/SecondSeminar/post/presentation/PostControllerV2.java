package com.example.SecondSeminar.post.presentation;

import com.example.SecondSeminar.post.application.PostServiceV2;
import com.example.SecondSeminar.post.dto.request.PostCreateRequest;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@RequestMapping("/api/v2/posts")
@RestController
public class PostControllerV2 {

    private static final String CUSTOM_AUTH_ID = "X-Auth-Id";
    private final PostServiceV2 postService;

    @PostMapping
    public ResponseEntity<Void> createPostV2(@RequestHeader(CUSTOM_AUTH_ID) Long memberId,
                                             @RequestPart MultipartFile image, PostCreateRequest request) {

        URI location = URI.create("/api/posts/v2" + postService.createV2(request, image, memberId));
        return ResponseEntity.created(location).build();
    }
}
