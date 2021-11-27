package com.powernote.skeleton.exception.servlet;

import com.powernote.skeleton.exception.error.MessageType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class ExceptionServletAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
        Integer status = (Integer) webRequest.getAttribute("javax.servlet.error.status_code", RequestAttributes.SCOPE_REQUEST);

        log.info("status 11 {}", status);

        if (status != HttpStatus.NOT_FOUND.value() && status != HttpStatus.FORBIDDEN.value()  ) {
            log.info("status 22 {}", status);
            return super.getErrorAttributes(webRequest, options);
        }

        Map<String, Object> errorAttributes = new HashMap<>();

        if( status == HttpStatus.NOT_FOUND.value() ){
            errorAttributes.put("timestamp", new Date().toString() );
            errorAttributes.put("status", HttpStatus.NOT_FOUND.value());
            errorAttributes.put("error", "404");
            errorAttributes.put("message",   MessageType.ERROR_PAGE_404.getMessage() );
            String  attributeNames = String.valueOf(webRequest.getAttribute("javax.servlet.forward.request_uri", 0)) ;
            errorAttributes.put("path", attributeNames );
            errorAttributes.put("errorCode", MessageType.ERROR_PAGE_404.toString() );
            errorAttributes.put("exception", "");
        }
        else if ( status == HttpStatus.FORBIDDEN.value() ) {
            errorAttributes.put("timestamp", new Date().toString() );
            errorAttributes.put("status", HttpStatus.FORBIDDEN.value());
            errorAttributes.put("error", "403");
            errorAttributes.put("message", MessageType.ERROR_PAGE_403.getMessage() );
            String  attributeNames = String.valueOf(webRequest.getAttribute("javax.servlet.forward.request_uri", 0)) ;
            errorAttributes.put("path", attributeNames );
            errorAttributes.put("errorCode", MessageType.ERROR_PAGE_403.toString() );
            errorAttributes.put("exception", "");
        }

        log.info("errorAttributes {}", errorAttributes.toString());

        return errorAttributes;
    }
}
