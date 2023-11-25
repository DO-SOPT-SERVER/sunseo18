package com.example.SecondSeminarKotlin.post.dto.response

import com.example.SecondSeminarKotlin.post.domain.Post

class PostGetResponse(
    val id: Long,
    val title: String,
    val content: String,
) {
    companion object {
        fun of(post: Post): PostGetResponse {
            return PostGetResponse(
                post.id,
                post.title,
                post.content,
            )
        }
    }
}