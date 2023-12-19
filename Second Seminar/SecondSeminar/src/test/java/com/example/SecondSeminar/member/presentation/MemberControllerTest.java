package com.example.SecondSeminar.member.presentation;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.SecondSeminar.member.application.MemberService;
import com.example.SecondSeminar.member.domain.Part;
import com.example.SecondSeminar.member.domain.SOPT;
import com.example.SecondSeminar.member.dto.response.MemberGetResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(MemberController.class)
public class MemberControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MemberService memberService;

    private final static String accessToken = "Bearer (accessToken)";

    @Test
    void getMemberProfileV2() throws Exception {
        Long memberId = 1L;
        String url = String.format("/api/member/%d/v2", memberId);

        MemberGetResponse response = new MemberGetResponse("정준서", "DDD", 24,
                new SOPT(31, Part.SERVER));

        given(memberService.getMemberByIdV2(any())).willReturn(response);

        mvc.perform(get(url).header("Authorization", accessToken))
                .andExpect(status().isOk())
                .andExpect(content().json(
                        "{\"name\": \"정준서\", \"nickname\": \"DDD\", \"age\":  24, \"soptInfo\": {\"generation\": 31, \"part\": \"SERVER\"}}"))
                .andDo(print());
    }


}
