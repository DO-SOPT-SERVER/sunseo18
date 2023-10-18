package com.example.SecondSeminarKotlin.presentation

import com.example.SecondSeminarKotlin.application.MemberService
import com.example.SecondSeminarKotlin.presentation.dto.request.MemberCreateRequest
import com.example.SecondSeminarKotlin.presentation.dto.request.MemberProfileUpdateRequest
import com.example.SecondSeminarKotlin.presentation.dto.response.MemberGetResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RequestMapping("/api/member")
@RestController
class MemberController(private val memberService: MemberService) {

    /*
     * 특정 멤버의 정보를 조회한다.
     */
    @GetMapping("/{memberId}/v1")
    fun getMemberProfileV1(@PathVariable("memberId") memberId: Long): ResponseEntity<MemberGetResponse> =
        ResponseEntity.ok(memberService.getMemberByIdV1(memberId))

    /*
     * 특정 멤버의 정보를 조회한다.
     */
    @GetMapping("/{memberId}/v2")
    fun getMemberProfileV2(@PathVariable("memberId") memberId: Long): ResponseEntity<MemberGetResponse> =
        ResponseEntity.ok(memberService.getMemberByIdV2(memberId))

    /*
     * 전체 멤버의 정보를 조회한다.
     */
    @GetMapping
    fun getMembersProfile(): ResponseEntity<List<MemberGetResponse>> =
        ResponseEntity.ok(memberService.getMembers())

    /*
     * 멤버를 생성한다.
     */
    @PostMapping
    fun createMember(@RequestBody request: MemberCreateRequest): ResponseEntity<Void> {
        val location: URI = URI.create("api/member/" + memberService.create(request))
        return ResponseEntity.created(location).build()
    }

    /*
     * 멤버의 SOPT 정보를 수정한다.
     */
    @PatchMapping("/{memberId}")
    fun updateMemberSoptInfo(
        @PathVariable memberId: Long,
        @RequestBody request: MemberProfileUpdateRequest,
    ): ResponseEntity<Void> {
        memberService.updateSOPT(memberId, request)
        return ResponseEntity.noContent().build()
    }

    /*
     * 특정 멤버를 삭제한다.
     */
    @DeleteMapping("/{memberId}")
    fun deleteMember(@PathVariable memberId: Long): ResponseEntity<Void> {
        memberService.deleteMember(memberId)
        return ResponseEntity.noContent().build()
    }
}
