package com.sns.ss.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.sns.ss.dto.request.MemberJoinRequest;
import com.sns.ss.dto.request.PostCreateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PostControllerTest {


    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;


    @DisplayName("포스트 - 작성")
    @Test
    @WithMockUser
    void postCreate() throws Exception {

        String title = "title";
        String body = "body";

        ResultActions actions =
                mvc.perform(
                        post("/api/v1/posts")
                                .accept(MediaType.APPLICATION_JSON)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsBytes(new PostCreateRequest(title, body)))
                );

        actions
                .andExpect(status().isOk());
    }

    @Test
    @WithAnonymousUser
    void postCreateNotLogin() throws Exception{

        ResultActions actions =
                        mvc.perform(
                                post("/api/v1/posts")
                                        .accept(MediaType.APPLICATION_JSON)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsBytes(new PostCreateRequest(title, body)))
                        );

                actions
                        .andExpect(status().isUnauthorized());

    }

}
