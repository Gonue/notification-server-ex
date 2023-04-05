package com.sns.ss.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table
@Entity
public class PostLike extends AuditingFields{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @ManyToOne
    @JoinColumn(name= "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "posd_id")
    private Post post;



    public static PostLike of(Member member, Post post){
        PostLike entity = new PostLike();
        entity.setMember(member);
        entity.setPost(post);
        return entity;
    }
}
