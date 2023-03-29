package com.sns.ss.dto;

import com.sns.ss.entity.Member;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberDto {

    private final Long memberId;
    private final String password;
    private final String email;
    private final String name;
    private final LocalDateTime createdAt;
    private final String createdBy;
    private final LocalDateTime modifiedAt;
    private final String modifiedBy;

    public static MemberDto of(Long memberId, String password, String email, String name){
        return new MemberDto(null, password, email, name, null,null,null,null);
    }

    public static MemberDto of(Long memberId, String password, String email, String name, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy){
        return new MemberDto(memberId, password, email, name, createdAt, createdBy, modifiedAt, modifiedBy);
    }



    public static MemberDto from(Member entity){
        return new MemberDto(
                entity.getMemberId(),
                entity.getPassword(),
                entity.getEmail(),
                entity.getName(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }

    public MemberDto toEntity(Member member){
        return MemberDto.of(
                memberId,
                password,
                email,
                name
        );
    }
}
