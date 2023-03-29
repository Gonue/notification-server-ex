package com.sns.ss.dto.response;

import com.sns.ss.dto.MemberDto;
import com.sns.ss.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MemberLoginResponse {

    private String token;

}
