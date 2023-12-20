package com.example.SeventhSeminarKotlin.Post.domain

import com.example.SeventhSeminarKotlin.Member.domain.Member
import com.example.SeventhSeminarKotlin.common.domain.BaseTimeEntity
import jakarta.persistence.*

@Entity
class Post(
    title: String,
    content: String,
    member: Member,
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    var title: String = title
        protected set

    @Column(columnDefinition = "TEXT")
    var content: String = content
        protected set

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    var member: Member = member

    fun editContent(content: String) {
        this.content = content
    }
}
