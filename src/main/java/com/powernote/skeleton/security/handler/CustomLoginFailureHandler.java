package com.powernote.skeleton.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.powernote.skeleton.dto.ResponseDto;
import com.powernote.skeleton.exception.error.MessageType;
import com.powernote.skeleton.util.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.util.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class CustomLoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    private String defaultUrl;

    public CustomLoginFailureHandler(String defaultUrl) {
        super.setDefaultFailureUrl( defaultUrl );
        this.defaultUrl = defaultUrl;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {


        String errMsg= "";
        String username = request.getParameter("username");
    	log.info("CustomLoginFailureHandler onAuthenticationFailure, role : {}, exception : {}, exception message : {}", request.getParameter("role"), request.getContextPath(), exception.toString(), exception.getMessage());

        if ( exception instanceof BadCredentialsException) {
            errMsg = "error.BadCredentials";
        } else if ( exception instanceof InternalAuthenticationServiceException) {
            errMsg = "error.InternalAuthentication";
        } else if ( exception instanceof DisabledException) {
            errMsg = "error.Disabled";
        } else if ( exception instanceof CredentialsExpiredException) {
            errMsg = "error.CredentialsExpired";
        } else {
            errMsg = "error.UnKnownError";
        }

        String isAjaxLogin = request.getHeader("isAjaxLogin");
        if (isAjaxLogin != null && "true".equals(isAjaxLogin)) {
            log.info("========= AJAX LOGIN REQUEST!!!! =========");

            //ajax 로 호출된 경우 json 으로 결과를 리턴한다.
            ObjectMapper om = new ObjectMapper();

            ResponseDto rDto = new ResponseDto( MessageType.ERROR_LOGIN_001 );
            rDto.setMessage( MessageUtil.getMessage(MessageType.ERROR_LOGIN_001.toString()) );
            rDto.setData( errMsg );

            response.setContentType("application/json;charset=UTF-8");

            String jsonString = om.writeValueAsString(rDto);
            OutputStream out = response.getOutputStream();
            out.write(jsonString.getBytes());

        }else {
           // redirect 시 setAttribute null 처리 됨.
//        request.setAttribute("errMsg", errMsg);
            response.sendRedirect( defaultUrl+"?errMsg="+errMsg );


//        아래와 같이 넘길시. request Header(기존 POST 요청) 로 인해  에 j_spring_security_check 페이지로 이동함.
//        request.setAttribute("errMsg", errMsg);
//        RequestDispatcher dispatcher = request.getRequestDispatcher(defaultUrl);
//        dispatcher.forward(request, response);
        }


    }


}