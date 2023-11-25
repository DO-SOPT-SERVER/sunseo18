package com.example.SecondSeminarKotlin.post.presentation

import com.example.SecondSeminarKotlin.post.application.PostService
import com.example.SecondSeminarKotlin.post.dto.request.PostCreateRequest
import com.example.SecondSeminarKotlin.post.dto.request.PostUpdateRequest
import com.example.SecondSeminarKotlin.post.dto.response.PostGetResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

private const val CUSTOM_AUTH_ID = "X-AUTH-ID"

@RequestMapping("/api/posts")
@RestController
class PostController(
    private val postService: PostService
) {

    @PostMapping
    fun createPost(
        @RequestHeader(CUSTOM_AUTH_ID) memberId: Long,
        @RequestBody request: PostCreateRequest
    ): ResponseEntity<Void> {

        val location: URI = URI.create("/api/post/" + postService.create(request, memberId))
        return ResponseEntity.created(location).build()
    }

    @GetMapping
    fun getPostsByMemberId(@RequestHeader(CUSTOM_AUTH_ID) memberId: Long): ResponseEntity<List<PostGetResponse>> {
        return ResponseEntity.ok(postService.getPostsByMemberId(memberId))
    }

    @GetMapping("{postId}")
    fun getPostById(
        @PathVariable postId: Long
    ): ResponseEntity<PostGetResponse> {
        return ResponseEntity.ok(postService.getPostById(postId))
    }

    @PatchMapping("{postId}")
    fun updatePost(@PathVariable postId: Long, @RequestBody request: PostUpdateRequest): ResponseEntity<Void> {

        postService.editContent(postId, request)
        return ResponseEntity.noContent().build()
    }

    @DeleteMapping("{postId}")
    fun deletePostById(@PathVariable postId: Long): ResponseEntity<Void> {
        postService.deleteById(postId);
        return ResponseEntity.noContent().build()
    }
}