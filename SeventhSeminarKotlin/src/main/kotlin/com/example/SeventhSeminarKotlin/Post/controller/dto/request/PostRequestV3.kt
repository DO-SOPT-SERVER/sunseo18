package com.example.SeventhSeminarKotlin.Post.controller.dto.request

class PostRequestV3(
    @ValidTitle
    val title: String,
    val content: String,
)
