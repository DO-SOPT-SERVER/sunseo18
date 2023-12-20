package com.example.SeventhSeminarKotlin.Member.domain

import com.example.SeventhSeminarKotlin.Post.domain.Post
import jakarta.persistence.*

@Entity
class Member(
    val name: String,
    val nickname: String,
    val age: Int,
    @Embedded
    var sopt: SOPT,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0

    @OneToMany(mappedBy = "member", cascade = [CascadeType.ALL])
    val posts: MutableList<Post> = mutableListOf()

    fun updateSOPT(sopt: SOPT) {
        this.sopt = sopt
    }
}
