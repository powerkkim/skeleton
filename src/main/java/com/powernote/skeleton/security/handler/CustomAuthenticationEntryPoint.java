package com.powernote.skeleton.security.handler;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)throws IOException, ServletException {
        // 에러 페이지에 대한 확장자를 현재 호출한 확장자와 마추어준다.
        response.setStatus( HttpStatus.UNAUTHORIZED.value() );
        request.setAttribute("errMsg", "로그인이 필요 합니다.");
        request.getRequestDispatcher("/error/error401").forward(request, response);
    }

}
