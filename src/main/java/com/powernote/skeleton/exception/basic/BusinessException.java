package com.powernote.skeleton.exception.basic;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * Service-Layer Exception
 * - 401 : UNAUTHORIZED : 인증오류
 * - 403 : FORBIDDEN  : 권한오류
 * - 409 : CONFLICT   : 업무처리예외
 * - 410 : GONE       : 중복요청
 */
@Setter
@Getter
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private HttpStatus httpStatus = HttpStatus.FORBIDDEN;
    private String messagecode = "";

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, HttpStatus httpStatus ) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public BusinessException(String message, String messageCode, HttpStatus httpStatus ) {
        super(message);
        this.messagecode = messageCode;
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
