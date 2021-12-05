package com.powernote.skeleton.dto;

import com.powernote.skeleton.exception.error.MessageType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDto<T> {

    private String result = "0000";  // result code
    private String message = "";      //
    private T data;

    public ResponseDto() {
        this.result = MessageType.OK.toString();
        this.message = MessageType.OK.getMessage();
        this.data = null;
    }

    public ResponseDto( MessageType result ) {
        this.result = result.toString();
        this.message = result.getMessage();
        this.data = null;
    }

    public ResponseDto( MessageType result, String message) {
        this.result = result.toString();
        this.message = message;
        this.data = null;
    }

    public ResponseDto( MessageType result, T data) {
        this.result = result.toString();
        this.message = result.getMessage();;
        this.data = data;
    }

    public ResponseDto( MessageType result,  String message, T data) {
        this.result = result.toString();
        this.message = message;
        this.data = data;
    }

    public String toString() {
        return "ApiResponseVO [result=" + this.result + ", resultMessage=" + this.message + ", data=" + this.data + "]";
    }


}
