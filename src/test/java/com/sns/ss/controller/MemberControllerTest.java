package com.sns.ss.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sns.ss.dto.request.MemberJoinRequest;
import com.sns.ss.dto.request.MemberLoginRequest;
import com.sns.ss.dto.MemberDto;
import com.sns.ss.exception.ErrorCode;
import com.sns.ss.exception.SnsApplicationException;
import com.sns.ss.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("컨트롤러 테스트 - 회원등록, 로그인")
@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerTest {

    private final MockMvc mvc;
    private final ObjectMapper objectMapper;

    public MemberControllerTest(@Autowired MockMvc mvc,
                                @Autowired ObjectMapper objectMapper) {
        this.mvc = mvc;
        this.objectMapper = objectMapper;
    }

    @MockBean
    private MemberService memberService;

    @DisplayName("회원가입 - 정상")
    @Test
    public void member_register_controller_test_isOk() throws Exception {
        //Given
        String email = "email";
        String name = "name";
        String password = "password";

        when(memberService.join(email,name,password)).thenReturn(mock(MemberDto.class));

        //When & Then
        ResultActions actions =
                mvc.perform(
                        post("/api/v1/members/join")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsBytes(new MemberJoinRequest(email,name,password)))
                );

        actions
                .andExpect(status().isOk());
    }

    @DisplayName("회원가입 - 이미 존재하는 데이터로 가입하는 경우 - 에러반환")
    @Test
    public void member_register_controller_test_errorCase1() throws Exception {
        //Given
        String name = "name";
        String email = "email";
        String password = "password";

        when(memberService.join(email,name,password)).thenThrow(new SnsApplicationException(ErrorCode.DUPLICATED_EMAIL, ""));

        //When & Then
        ResultActions actions =
                mvc.perform(
                        post("/api/v1/members/join")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsBytes(new MemberJoinRequest(email,name,password)))
                );

        actions
                .andExpect(status().isConflict());
    }

}
