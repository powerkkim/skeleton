package com.powernote.skeleton.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.nio.charset.Charset;

public class CommonUtil {

    //restAPI 리턴 시 공통 헤더 정의
    public static HttpHeaders getHeader(){

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        return  headers;
    }
}
