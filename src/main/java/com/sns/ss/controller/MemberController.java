package com.sns.ss.controller;
import com.sns.ss.dto.MemberDto;
import com.sns.ss.dto.request.MemberJoinRequest;
import com.sns.ss.dto.request.MemberLoginRequest;
import com.sns.ss.dto.response.MemberJoinResponse;
import com.sns.ss.dto.response.MemberLoginResponse;
import com.sns.ss.dto.response.Response;
import com.sns.ss.service.MemberService;
import org.springframework.http.ResponseEntity;
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

//    @PostMapping("/login")
//    public Response<MemberLoginResponse> login(@RequestBody MemberLoginRequest request){
//        String token = memberService.login(request.getName(), request.getPassword());
//        return Response.success(new MemberLoginResponse(token));
//    }
}
