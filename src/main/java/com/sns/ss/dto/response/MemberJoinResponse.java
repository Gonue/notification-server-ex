package com.sns.ss.dto.response;

import com.sns.ss.dto.MemberDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberJoinResponse {

    private final Long memberId;
    private final String email;
    private final String name;
    private final LocalDateTime createAt;


    public static MemberJoinResponse of(Long memberId, String email, String name){
        return MemberJoinResponse.of(memberId, email, name, null);
    }
    public static MemberJoinResponse of(Long memberId, String email, String name, LocalDateTime createAt){
        return new MemberJoinResponse(memberId, email, name, createAt);
    }

    public static MemberJoinResponse from(MemberDto dto){
        return new MemberJoinResponse(
                dto.getMemberId(),
                dto.getEmail(),
                dto.getName(),
                dto.getCreatedAt()
        );
    }

}
