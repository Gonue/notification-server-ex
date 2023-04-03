package com.sns.ss.dto.response;

import com.sns.ss.dto.PostDto;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class PostResponse {
    private final Long postId;
    private final String title;
    private final String body;
    private final MemberResponse member;
    private final LocalDateTime createdAt;
    private final String createdBy;
    private final LocalDateTime modifiedAt;
    private final String modifiedBy;

    public static PostResponse from(PostDto dto) {
        return new PostResponse(
                dto.getPostId(),
                dto.getTitle(),
                dto.getBody(),
                MemberResponse.from(dto.getMember()),
                dto.getCreatedAt(),
                dto.getCreatedBy(),
                dto.getModifiedAt(),
                dto.getModifiedBy()
        );
    }
}
