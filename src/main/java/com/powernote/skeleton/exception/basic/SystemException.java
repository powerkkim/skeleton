package com.powernote.skeleton.exception.basic;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * ClientException, BuisinessException 이외의 오류
 * - 500 : INTERNAL : 시스템오류
 */
@Setter
@Getter
public class SystemException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    private String messagecode = "";

    public SystemException(String message) {
        super(message);
    }

    public SystemException(String message, HttpStatus httpStatus ) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public SystemException(String message, String messageCode, HttpStatus httpStatus ) {
        super(message);
        this.messagecode = messageCode;
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
