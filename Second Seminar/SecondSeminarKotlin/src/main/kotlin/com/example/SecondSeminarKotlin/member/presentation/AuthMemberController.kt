package com.example.SecondSeminarKotlin.member.presentation

import com.example.SecondSeminarKotlin.member.application.AuthMemberService
import com.example.SecondSeminarKotlin.member.dto.request.AuthMemberRequest
import com.example.SecondSeminarKotlin.member.dto.response.AuthMemberSigninResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RequestMapping("/api/users")
@RestController
class AuthMemberController(
    private val authMemberService: AuthMemberService,
) {

    @PostMapping("/sign-up")
    fun signUp(@RequestBody request: AuthMemberRequest): ResponseEntity<Void> {
        val location = URI.create(authMemberService.create(request))
        return ResponseEntity.created(location).build()
    }

    @PostMapping("/sign-in")
    fun signIn(@RequestBody request: AuthMemberRequest): ResponseEntity<AuthMemberSigninResponse> {
        return ResponseEntity.ok(authMemberService.signIn(request))
    }
}