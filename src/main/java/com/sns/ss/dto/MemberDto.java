package com.sns.ss.dto;

import com.sns.ss.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MemberDto {

    private Long id;
    private String name;
    private String password;
    private Member.MemberRole memberRole;
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;

    public static MemberDto from(Member entity){
        return new MemberDto(
                entity.getId(),
                entity.getName(),
                entity.getPassword(),
                entity.getRole(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }
}
