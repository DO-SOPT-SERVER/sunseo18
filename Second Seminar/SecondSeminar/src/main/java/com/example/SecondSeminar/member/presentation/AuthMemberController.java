package com.example.SecondSeminar.member.presentation;

import com.example.SecondSeminar.member.application.AuthMemberService;
import com.example.SecondSeminar.member.dto.request.AuthMemberRequest;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/users/")
@RequiredArgsConstructor
@RestController
public class AuthMemberController {

    private final AuthMemberService authMemberService;

    @PostMapping("sign-up")
    public ResponseEntity<Void> signUp(@RequestBody AuthMemberRequest request) {
        URI location = URI.create(authMemberService.create(request));
        return ResponseEntity.created(location).build();
    }

    @PostMapping("sign-in")
    public ResponseEntity<Void> signIn(@RequestBody AuthMemberRequest request) {
        authMemberService.signIn(request);
        return ResponseEntity.noContent().build();
    }
}
