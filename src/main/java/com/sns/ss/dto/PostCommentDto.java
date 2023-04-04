package com.sns.ss.dto;
import com.sns.ss.entity.Post;
import com.sns.ss.entity.PostComment;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostCommentDto {
    private final Long postCommentId;
    private final String comment;
    private final String email;
    private final Long postId;
    private final LocalDateTime createdAt;
    private final String createdBy;
    private final LocalDateTime modifiedAt;
    private final String modifiedBy;


    public static PostCommentDto from(PostComment entity){
        return new PostCommentDto(
                entity.getPostCommentId(),
                entity.getComment(),
                entity.getMember().getEmail(),
                entity.getPost().getPostId(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }
}
