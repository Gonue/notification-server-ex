package com.sns.ss.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
@Table(indexes = {
        @Index(columnList = "title"),
})
@Setter
@Getter
@Entity
public class Post extends AuditingFields{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String body;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;



    public static Post of(String title, String body, Member member){
        Post post = new Post();
        post.setTitle(title);
        post.setBody(body);
        post.setMember(member);
        return post;

    }
}
