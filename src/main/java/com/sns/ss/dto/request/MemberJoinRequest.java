package com.sns.ss.dto.request;


import lombok.Data;

@Data
public class MemberJoinRequest {

    private final String email;
    private final String name;
    private final String password;

    public static MemberJoinRequest of(String password, String name, String email){
        return new MemberJoinRequest(password, name, email);
    }
}
