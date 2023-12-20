package com.example.SeventhSeminarKotlin.Post.domain

import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long>
