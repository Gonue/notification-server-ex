package com.sns.ss.dto.response;
import com.sns.ss.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberResponse {

    private final Long memberId;
    private String email;

    public static MemberResponse from(MemberDto dto) {
        return new MemberResponse(
                dto.getMemberId(),
                dto.getEmail()
        );
    }
}
