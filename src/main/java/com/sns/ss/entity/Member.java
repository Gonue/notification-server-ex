package com.sns.ss.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Table
@Getter
@Setter
@ToString(callSuper = true)
@Entity
public class Member extends AuditingFields{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String password;
    @Enumerated(value = EnumType.STRING)
    private MemberRole role = MemberRole.MEMBER;


    public enum MemberRole {
        ADMIN,
        MEMBER
    }

    public static Member of(String name, String password) {
        Member member = new Member();
        member.setName(name);
        member.setPassword(password);
        return member;
    }

}
