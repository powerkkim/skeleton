package com.powernote.skeleton.exception.error;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorRes {
    private String timestamp;
    private String status;
    private String error;
    private String message;
    private String path;
    private String errorCode;
    private String exception;

    @Builder
    private ErrorRes(String timestamp, String status, String error, String message, String path, String errorCode, String exception) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.errorCode = errorCode;
        this.exception = exception;
    }
}
