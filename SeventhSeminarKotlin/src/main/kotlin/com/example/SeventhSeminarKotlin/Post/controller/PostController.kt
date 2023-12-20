package com.example.SeventhSeminarKotlin.Post.controller

import com.example.SeventhSeminarKotlin.Post.controller.dto.request.PostRequest
import com.example.SeventhSeminarKotlin.Post.controller.dto.request.PostRequestV3
import com.example.SeventhSeminarKotlin.Post.service.PostService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

private const val memberIdHeader = "X-AUTH-ID"

@RequestMapping("/posts")
@RestController
class PostController(
    val postService: PostService,
) {

    @PostMapping
    fun createPost(
        @RequestHeader(memberIdHeader)
        memberId: Long,
        @Valid @RequestBody
        request: PostRequest,
    ): ResponseEntity<Void> {
        postService.create(memberId, request)
        return ResponseEntity.ok().build()
    }

    @PostMapping("/v3")
    fun createPostV3(
        @RequestHeader(memberIdHeader)
        memberId: Long,
        @Valid @RequestBody
        request: PostRequestV3,
    ): ResponseEntity<Void> {
        postService.createV3(memberId, request)
        return ResponseEntity.ok().build()
    }
}
