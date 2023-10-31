package com.example.SecondSeminar.member.presentation;

import com.example.SecondSeminar.member.dto.request.MemberCreateRequest;
import com.example.SecondSeminar.member.dto.request.MemberProfileUpdateRequest;
import com.example.SecondSeminar.member.dto.response.MemberGetResponse;
import com.example.SecondSeminar.member.application.MemberService;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;

    /*
     * 특정 멤버의 정보를 조회한다.
     */
    @GetMapping("/{memberId}/v1")
    public ResponseEntity<MemberGetResponse> getMemberProfileV1(@PathVariable("memberId") Long memberId) {
        return ResponseEntity.ok(memberService.getMemberByIdV1(memberId));
    }

    /*
     * 특정 멤버의 정보를 조회한다.
     */
    //consumes: 어떤 요청을 받을지
    //produces: 어떤 응답을 리턴할지 근데 오타가 날 수 있기 때문에 문자열로 하드코딩 하지 말자 웬만하면!
    @GetMapping(value = "/{memberId}/v2", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MemberGetResponse> getMemberProfileV2(@PathVariable Long memberId) {
        return ResponseEntity.ok(memberService.getMemberByIdV2(memberId));
    }

    /*
     * 전체 멤버 정보를 조회한다.
     */
    @GetMapping
    public ResponseEntity<List<MemberGetResponse>> getMembersProfile() {
        return ResponseEntity.ok(memberService.getMembers());
    }

    /*
     * 멤버를 생성한다.
     */
    @PostMapping
    public ResponseEntity<Void> createMember(@RequestBody MemberCreateRequest request) {
        URI location = URI.create("api/member/" + memberService.create(request));
        return ResponseEntity.created(location).build();
    }

    /*
     * 멤버의 SOPT 정보를 수정한다.
     */
    @PatchMapping("/{memberId}")
    public ResponseEntity<Void> updateMemberSoptInfo(@PathVariable Long memberId,
                                                     @RequestBody MemberProfileUpdateRequest request) {
        memberService.updateSOPT(memberId, request);
        return ResponseEntity.noContent().build();
    }

    /*
     * 특정 멤버를 삭제한다.
     */
    @DeleteMapping("/{memberId}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long memberId) {
        memberService.deleteMember(memberId);
        return ResponseEntity.noContent().build();
    }
}
