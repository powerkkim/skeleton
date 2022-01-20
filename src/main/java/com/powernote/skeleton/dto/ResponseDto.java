package com.powernote.skeleton.dto;

import com.powernote.skeleton.exception.error.MessageType;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseDto<T> {
    private String result = "0000";  // result code
    private String message = "";      //
    private T data;

    public ResponseDto() {
        this.result = MessageType.OK.toString();
        this.message = MessageType.OK.getMessage();
        this.data = null;
    }

    public ResponseDto( MessageType messageType ) {
        this.result = messageType.toString();
        this.message = messageType.getMessage();
        this.data = null;
    }

    public ResponseDto( MessageType messageType, String message) {
        this.result = messageType.toString();
        this.message = message;
        this.data = null;
    }

    public ResponseDto( MessageType messageType, T data) {
        this.result = messageType.toString();
        this.message = messageType.getMessage();;
        this.data = data;
    }

    public ResponseDto( String result,  String message, T data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public String toString() {
        return "ApiResponseVO [result=" + this.result + ", resultMessage=" + this.message + ", data=" + this.data + "]";
    }


}
