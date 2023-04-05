package com.sns.ss.controller;
import com.sns.ss.dto.request.MemberJoinRequest;
import com.sns.ss.dto.response.AlarmResponse;
import com.sns.ss.dto.response.MemberJoinResponse;
import com.sns.ss.dto.response.Response;
import com.sns.ss.service.MemberService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/join")
    public Response<MemberJoinResponse> join(@RequestBody MemberJoinRequest memberJoinRequest){
        return Response.success(MemberJoinResponse.from(memberService.join(memberJoinRequest.getEmail(), memberJoinRequest.getPassword(), memberJoinRequest.getName())));
    }

    @GetMapping("/user")
    public ResponseEntity<?> test1(){
        return ResponseEntity.ok("success");
    }
    @GetMapping("/admin")
    public ResponseEntity<?> test2(){
        return ResponseEntity.ok("success");
    }

    @GetMapping("/alarm")
    public Response<Page<AlarmResponse>> alarm(Pageable pageable, Authentication authentication){
        return Response.success(memberService.alarmList(authentication.getName(), pageable).map(AlarmResponse::from));
    }
}
