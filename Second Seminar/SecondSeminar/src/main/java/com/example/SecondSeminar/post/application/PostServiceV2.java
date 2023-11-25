package com.example.SecondSeminar.post.application;

import com.example.SecondSeminar.external.S3Service;
import com.example.SecondSeminar.external.exception.S3Exception;
import com.example.SecondSeminar.external.exception.S3ExceptionType;
import com.example.SecondSeminar.member.domain.Member;
import com.example.SecondSeminar.member.domain.MemberJpaRepository;
import com.example.SecondSeminar.post.domain.Post;
import com.example.SecondSeminar.post.domain.PostJpaRepository;
import com.example.SecondSeminar.post.dto.request.PostCreateRequest;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class PostServiceV2 {
    private static final String POST_IMAGE_FOLDER_NAME = "posts/";

    private final MemberJpaRepository memberJpaRepository;
    private final PostJpaRepository postJpaRepository;
    private final S3Service s3Service;

    @Transactional
    public String createV2(PostCreateRequest request, MultipartFile image, Long memberId) {
        try {
            final String imageUrl = s3Service.uploadImage(POST_IMAGE_FOLDER_NAME, image);

            Member member = memberJpaRepository.findByIdOrThrowException(memberId);

            Post post = postJpaRepository.save(
                    Post.builderWithImageUrl()
                            .title(request.title())
                            .content(request.content())
                            .imageUrl(imageUrl)
                            .member(member)
                            .build()
            );

            return post.getId().toString();
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        } catch (IOException e) {
            throw new S3Exception(S3ExceptionType.UPLOAD_FAILED);
        }

    }
}