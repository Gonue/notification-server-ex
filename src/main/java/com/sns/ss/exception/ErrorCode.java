package com.sns.ss.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    DUPLICATED_MEMBER_NAME(HttpStatus.CONFLICT, "member name is duplicated");

    private HttpStatus status;
    private String message;
}
