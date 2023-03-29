package com.sns.ss.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public class ErrorResponse {
    private HttpStatus status;
    private String message;

    private ErrorResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }


    public static ErrorResponse of(ErrorCode exceptionCode) {
        return new ErrorResponse(exceptionCode.getStatus(), exceptionCode.getMessage());
    }

    public static ErrorResponse of(HttpStatus httpStatus) {
        return new ErrorResponse(HttpStatus.valueOf(httpStatus.value()), httpStatus.getReasonPhrase());
    }

    public static ErrorResponse of(HttpStatus httpStatus, String message) {
        return new ErrorResponse(HttpStatus.valueOf(httpStatus.value()), message);
    }
}