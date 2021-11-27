package com.powernote.skeleton.exception.basic;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * Controller-Layer Exception
 * - 400 : BAD_REQUEST : 요청데이터 오류
 * - 404 : NOT_FOUND   : 데이터없음
 */
@Setter
@Getter
public class ClientException  extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
    private String messagecode = "";

    public ClientException(String message) {
        super(message);
    }

    public ClientException(String message, HttpStatus httpStatus ) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public ClientException(String message, String messageCode, HttpStatus httpStatus ) {
        super(message);
        this.messagecode = messageCode;
        this.httpStatus = httpStatus;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
