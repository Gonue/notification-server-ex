package com.sns.ss.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class MemberJoinRequest {

    private String name;
    private String password;

    public static MemberJoinRequest of(String name, String content){
        return new MemberJoinRequest(name, content);
    }
}
