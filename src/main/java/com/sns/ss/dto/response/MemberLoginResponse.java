package com.sns.ss.dto.response;

import com.sns.ss.exception.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MemberLoginResponse {

    private HttpStatus resultCode;
    private String result;

    public MemberLoginResponse(HttpStatus resultCode, String result) {
        this.resultCode = resultCode;
        this.result = result;
    }

    public static MemberLoginResponse of(ErrorCode exceptionCode) {
        return new MemberLoginResponse(exceptionCode.getStatus(), exceptionCode.getMessage());
    }

    public static MemberLoginResponse of(HttpStatus httpStatus) {
        return new MemberLoginResponse(HttpStatus.valueOf(httpStatus.value()), httpStatus.getReasonPhrase());
    }

    public static MemberLoginResponse of(HttpStatus httpStatus, String message) {
        return new MemberLoginResponse(HttpStatus.valueOf(httpStatus.value()), message);
    }


}
