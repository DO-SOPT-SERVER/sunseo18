package com.example.SeventhSeminarKotlin.Post.controller.dto.request

import jakarta.validation.constraints.Size

class PostRequest(
    @field:Size(min = 10, max = 10, message = "제목은 1글자 이상 10글자 이하여야합니다.")
    val title: String,
    val content: String,
)
