package com.example.SeventhSeminarKotlin.Post.service

import com.example.SeventhSeminarKotlin.Member.service.MemberService
import com.example.SeventhSeminarKotlin.Post.controller.dto.request.PostRequest
import com.example.SeventhSeminarKotlin.Post.controller.dto.request.PostRequestV3
import com.example.SeventhSeminarKotlin.Post.domain.Post
import com.example.SeventhSeminarKotlin.Post.domain.PostRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class PostService(
    private val postRepository: PostRepository,
    private val memberService: MemberService,
) {

    @Transactional
    fun create(memberId: Long, request: PostRequest) {
        val member = memberService.getByIdOrElseThrowException(memberId)
        postRepository.save(Post(title = request.title, content = request.content, member = member))
    }

    @Transactional
    fun createV3(memberId: Long, request: PostRequestV3) {
        val member = memberService.getByIdOrElseThrowException(memberId)
        postRepository.save(Post(title = request.title, content = request.content, member = member))
    }
}
