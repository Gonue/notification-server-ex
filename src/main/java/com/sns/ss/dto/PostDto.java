package com.sns.ss.dto;
import com.sns.ss.entity.Post;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostDto {
    private final Long postId;
    private final String title;
    private final String body;
    private final MemberDto member;
    private final LocalDateTime createdAt;
    private final String createdBy;
    private final LocalDateTime modifiedAt;
    private final String modifiedBy;


    public static PostDto from(Post entity){
        return new PostDto(
                entity.getPostId(),
                entity.getTitle(),
                entity.getBody(),
                MemberDto.from(entity.getMember()),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }
}
