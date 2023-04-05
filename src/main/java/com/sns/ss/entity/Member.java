package com.sns.ss.entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
public class Member extends AuditingFields{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(length = 100, nullable = false)
    private String password;
    @Column(nullable = false, updatable = false, unique = true)
    private String email;
    @Column(length = 100, nullable = false)
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    public enum MemberRole {
        ROLE_USER,
        ROLE_ADMIN
    }

    public Member() {} //TODO 테스트 코드 수정 후 수정

    public Member(Long memberId, String password, String email, String name, String createdBy){
        this.memberId = memberId;
        this.password = password;
        this.email = email;
        this.name = name;
        this.createdBy = createdBy;
        this.modifiedBy = createdBy;
    }


    public static Member of(String email, String name, String encodedPwd){
        Member entity = new Member();
        entity.setEmail(email);
        entity.setName(name);
        entity.setPassword(encodedPwd);

        return entity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member that = (Member) o;
        return Objects.equals(this.getMemberId(), that.memberId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getMemberId());
    }
}
