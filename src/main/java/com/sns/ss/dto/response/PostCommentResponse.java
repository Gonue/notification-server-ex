package com.sns.ss.dto.response;

import com.sns.ss.dto.PostCommentDto;
import com.sns.ss.dto.PostDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class PostCommentResponse {
    private final Long postCommentId;
    private final String comment;
    private final String email;
    private final Long postId;
    private final LocalDateTime createdAt;
    private final String createdBy;
    private final LocalDateTime modifiedAt;
    private final String modifiedBy;

    public static PostCommentResponse from(PostCommentDto dto) {
        return new PostCommentResponse(
                dto.getPostCommentId(),
                dto.getComment(),
                dto.getEmail(),
                dto.getPostId(),
                dto.getCreatedAt(),
                dto.getCreatedBy(),
                dto.getModifiedAt(),
                dto.getModifiedBy()
        );
    }
}
