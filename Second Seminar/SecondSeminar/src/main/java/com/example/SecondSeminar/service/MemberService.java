package com.example.SecondSeminar.service;

import com.example.SecondSeminar.controller.dto.request.MemberCreateRequest;
import com.example.SecondSeminar.controller.dto.request.MemberProfileUpdateRequest;
import com.example.SecondSeminar.controller.dto.response.MemberGetResponse;
import com.example.SecondSeminar.domain.Member;
import com.example.SecondSeminar.domain.SOPT;
import com.example.SecondSeminar.domain.repository.MemberJpaRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberJpaRepository memberJpaRepository;

    public MemberGetResponse getMemberByIdV1(Long memberId) {
        //Optional 값을 검사 없이 냅다 가져오는 중
        return MemberGetResponse.of(memberJpaRepository.findById(memberId).get());
    }

    public MemberGetResponse getMemberByIdV2(Long memberId) {
//        Member member = memberJpaRepository.findById(memberId)
//                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 회원입니다"));
//        return MemberGetResponse.of(member);
//        아래처럼 default 메서드 해줄 수도 있음 위 방식으로 메서드 구현을 할 거면 findById 메서드를 따로 뽑아내면 좋을듯!
        return MemberGetResponse.of(memberJpaRepository.findByIdOrThrowException(memberId));
    }

    public List<MemberGetResponse> getMembers() {
//        return memberJpaRepository.findAll().stream().map(MemberGetResponse::of).collect(Collectors.toList());
        return memberJpaRepository.findAll().stream().map(MemberGetResponse::of).toList();
    }

    @Transactional
    public String create(MemberCreateRequest request) {
        Member member = Member.builder()
                .name(request.name())
                .nickname(request.nickname())
                .age(request.age())
                .sopt(request.sopt())
                .build();

        return memberJpaRepository.save(member).getId().toString();
        // 그냥 저장만 하는 save(),
        // 저장하고 나서 저장된 Member 리턴하는 save()가 있음
    }

    @Transactional
    public void updateSOPT(Long memberId, MemberProfileUpdateRequest request) {
        Member member = memberJpaRepository.findByIdOrThrowException(memberId);
        member.updateSOPT(new SOPT(request.generation(), request.part()));
    }

    @Transactional
    public void deleteMember(Long memberId) {
        Member member = memberJpaRepository.findByIdOrThrowException(memberId);
        memberJpaRepository.delete(member);
    }
}
