package com.example.SecondSeminarKotlin.post.application

import com.example.SecondSeminarKotlin.member.application.MemberService
import com.example.SecondSeminarKotlin.post.domain.Post
import com.example.SecondSeminarKotlin.post.domain.PostJpaRepository
import com.example.SecondSeminarKotlin.post.dto.request.PostCreateRequest
import com.example.SecondSeminarKotlin.post.dto.request.PostUpdateRequest
import com.example.SecondSeminarKotlin.post.dto.response.PostGetResponse
import com.example.SecondSeminarKotlin.post.exception.PostException
import com.example.SecondSeminarKotlin.post.exception.PostExceptionType
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class PostService(
    private val postJpaRepository: PostJpaRepository,
    private val memberJpaRepository: PostJpaRepository,
    private val memberService: MemberService
) {

    @Transactional
    fun create(request: PostCreateRequest, memberId: Long): String {
        val member = memberService.findMemberById(memberId)

        val post = postJpaRepository.save(
            Post(
                member = member,
                title = request.title,
                content = request.content,
                imageUrl = null
            )
        )

        return post.id.toString()
    }

    fun getPostsByMemberId(memberId: Long): List<PostGetResponse> {
        val member = memberService.findMemberById(memberId)

        return postJpaRepository.findAllByMemberId(member.id)
            .map(PostGetResponse::of)
    }

    fun getPostById(postId: Long): PostGetResponse {
        val post = findPostById(postId)
        return PostGetResponse.of(post)
    }

    @Transactional
    fun editContent(postId: Long, request: PostUpdateRequest) {
        val post = findPostById(postId)

        post.editContent(request.content)
    }


    @Transactional
    fun deleteById(postId: Long) {
        findPostById(postId)
        postJpaRepository.deleteById(postId)
    }

    fun findPostById(postId: Long) =
        postJpaRepository.findByIdOrNull(postId) ?: throw PostException(PostExceptionType.NOT_FOUND_POST)

}