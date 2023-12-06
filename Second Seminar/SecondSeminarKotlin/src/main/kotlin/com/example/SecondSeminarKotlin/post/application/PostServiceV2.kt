package com.example.SecondSeminarKotlin.post.application

import com.example.SecondSeminarKotlin.exception.BaseCustomException
import com.example.SecondSeminarKotlin.external.S3Service
import com.example.SecondSeminarKotlin.member.application.MemberService
import com.example.SecondSeminarKotlin.post.domain.Post
import com.example.SecondSeminarKotlin.post.domain.PostJpaRepository
import com.example.SecondSeminarKotlin.post.dto.request.PostCreateRequest
import com.example.SecondSeminarKotlin.post.exception.PostExceptionType
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

private const val POST_IMAGE_FOLDER_NAME = "posts/"

@Transactional(readOnly = true)
@Service
class PostServiceV2(
    private val memberService: MemberService,
    private val postJpaRepository: PostJpaRepository,
    private val s3Service: S3Service,
) {

    @Transactional
    fun createV2(request: PostCreateRequest, image: MultipartFile, memberId: Long): String {
        val imageUrl = s3Service.uploadImage(POST_IMAGE_FOLDER_NAME, image)

        val member = memberService.findMemberById(memberId)

        val post = postJpaRepository.save(
            Post(
                title = request.title,
                content = request.content,
                imageUrl = imageUrl,
                member = member
            )
        )

        return post.id.toString()
    }

    @Transactional
    fun deleteByIdV2(postId: Long) {
        val post: Post =
            postJpaRepository.findByIdOrNull(postId) ?: throw BaseCustomException(PostExceptionType.NOT_FOUND_POST)

        // post에 imageUrl 정보가 있을 때만 이미지 삭제
        if (!post.imageUrl.isNullOrBlank())
            s3Service.deleteImage(post.imageUrl!!)

        postJpaRepository.deleteById(postId)
    }
}