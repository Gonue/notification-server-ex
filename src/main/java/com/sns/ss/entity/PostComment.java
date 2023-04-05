package com.sns.ss.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Table(indexes = {
        @Index(columnList = "comment"),
})
@Getter
@Setter
@Entity
public class PostComment extends AuditingFields{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postCommentId;

    @ManyToOne
    @JoinColumn(name= "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "posd_id")
    private Post post;

    private String comment;



    public static PostComment of(Member member, Post post, String comment){
        PostComment entity = new PostComment();
        entity.setMember(member);
        entity.setPost(post);
        entity.setComment(comment);
        return entity;
    }
}
