package com.example.SecondSeminar.member.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatList;
import static org.mockito.BDDMockito.given;

import com.example.SecondSeminar.member.domain.Member;
import com.example.SecondSeminar.member.domain.MemberJpaRepository;
import com.example.SecondSeminar.member.domain.Part;
import com.example.SecondSeminar.member.domain.SOPT;
import com.example.SecondSeminar.member.dto.request.MemberProfileUpdateRequest;
import com.example.SecondSeminar.member.dto.response.MemberGetResponse;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {

    @Mock
    MemberJpaRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    @Test
    void getMemberByIdV2() {
        Long memberId = 1L;
        SOPT soptInfo = new SOPT(31, Part.SERVER);
        Member member = new Member("정준서", "DDD", 24, soptInfo);
        given(memberRepository.findByIdOrThrowException(memberId)).willReturn(member);

        MemberGetResponse response = memberService.getMemberByIdV2(memberId);
        assertThat(response.name()).isEqualTo("정준서");
        assertThat(response.nickname()).isEqualTo("DDD");
        assertThat(response.age()).isEqualTo(24);
        assertThat(response.soptInfo()).isEqualTo(soptInfo);
    }


    @Test
    void getMembers() {
        Member member1 = new Member("정준서", "DDD", 24, new SOPT(31, Part.SERVER));
        Member member2 = new Member("김태욱", "발라더", 25, new SOPT(33, Part.SERVER));
        Member member3 = new Member("정홍준", "아빠", 27, new SOPT(32, Part.SERVER));

        given(memberRepository.findAll()).willReturn(List.of(member1, member2, member3));

        List<MemberGetResponse> responseList = memberService.getMembers();
        assertThat(responseList).isInstanceOf(List.class);
        assertThatList(responseList).extracting("name").contains("정준서", "김태욱", "정홍준");
        assertThatList(responseList).extracting("nickname").contains("DDD", "발라더", "아빠");
        assertThatList(responseList).extracting("age").contains(24, 25, 27);
    }

    @Test
    void updateSOPT() {
        Long memberId = 1L;
        SOPT soptInfo = new SOPT(31, Part.SERVER);
        Member member = new Member("정준서", "DDD", 24, soptInfo);
        MemberProfileUpdateRequest request = new MemberProfileUpdateRequest(33, Part.SERVER);

        given(memberRepository.findByIdOrThrowException(memberId)).willReturn(member);

        memberService.updateSOPT(1L, request);
    }

}
