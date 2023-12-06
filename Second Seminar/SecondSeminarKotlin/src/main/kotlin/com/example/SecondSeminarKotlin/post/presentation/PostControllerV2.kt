package com.example.SecondSeminarKotlin.post.presentation

import com.example.SecondSeminarKotlin.post.application.PostServiceV2
import com.example.SecondSeminarKotlin.post.dto.request.PostCreateRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.net.URI

private const val CUSTOM_AUTH_ID = "X-Auth-Id"

@RestController
@RequestMapping("/api/v2/posts")
class PostControllerV2(
    private val postService: PostServiceV2
) {

    @PostMapping
    fun createPostV2(
        @RequestHeader(CUSTOM_AUTH_ID) memberId: Long,
        @RequestPart image: MultipartFile,
        request: PostCreateRequest
    ): ResponseEntity<Void> {
        val location = URI.create("/api/posts/v2/" + postService.createV2(request, image, memberId))
        return ResponseEntity.created(location).build()
    }

    @DeleteMapping("{postId}")
    fun deletePost(@PathVariable postId: Long): ResponseEntity<Void> {
        postService.deleteByIdV2(postId)
        return ResponseEntity.noContent().build()
    }
}