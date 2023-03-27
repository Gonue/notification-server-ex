package com.sns.ss.dto.response;

import com.sns.ss.dto.MemberDto;
import com.sns.ss.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberJoinResponse {

    private Long id;
    private String name;
    private Member.MemberRole role;

    public static MemberJoinResponse from (MemberDto memberDto){
        return new MemberJoinResponse(
                memberDto.getId(),
                memberDto.getName(),
                memberDto.getMemberRole()
        );
    }
}
