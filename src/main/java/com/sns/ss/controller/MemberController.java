package com.sns.ss.controller;
import com.sns.ss.dto.MemberDto;
import com.sns.ss.dto.request.MemberJoinRequest;
import com.sns.ss.dto.response.MemberJoinResponse;
import com.sns.ss.dto.response.Response;
import com.sns.ss.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {


    private final MemberService memberService;

    @PostMapping("/join")
    public Response<MemberJoinResponse> join(@RequestBody MemberJoinRequest request){
        MemberDto memberDto = memberService.join(request.getName(),request.getPassword());
        return Response.success(MemberJoinResponse.from(memberDto));
    }
}
