package com.powernote.skeleton.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
    	log.info("onAuthenticationFailure, role : {}, exception : {}, exception message : {}", request.getParameter("role"), request.getContextPath(), exception.toString(), exception.getMessage());

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

        request.setAttribute("errMsg", errMsg);

        response.sendRedirect( defaultUrl );
    }


}