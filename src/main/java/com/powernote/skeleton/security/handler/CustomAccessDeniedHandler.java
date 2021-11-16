package com.powernote.skeleton.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {


    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException ade) throws IOException, ServletException {
        // TODO Auto-generated method stub
        log.info("Exceiption : {}",ade);
        log.info("LocalizedMessage : {}",ade.getLocalizedMessage());
        log.info("Message : {}",ade.getMessage());
        log.info("StackTrace : {}",ade.getStackTrace());

        response.setStatus( HttpStatus.FORBIDDEN.value() );
        request.setAttribute("errMsg", "접근권한 없는 사용자 입니다.");
        request.getRequestDispatcher("/error/error403").forward(request, response);
    }
}
