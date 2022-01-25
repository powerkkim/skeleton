package com.powernote.skeleton.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.powernote.skeleton.dto.ResponseDto;
import com.powernote.skeleton.exception.error.MessageType;
import com.powernote.skeleton.util.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

@Slf4j
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RequestCache reqCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStratgy = new DefaultRedirectStrategy();

    private String defaultUrl;

//    private static int SESSION_TIME = 30; // 30 Minutes
    private static int SESSION_TIME = 60 * 30; // 30 Minutes

    public CustomLoginSuccessHandler(String defaultUrl) {
        super.setDefaultTargetUrl( defaultUrl );
        this.defaultUrl = defaultUrl;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth) throws IOException,
            ServletException {

        log.info("clearAuthenticationAttributrs");

        clearAuthenticationAttributes(request);

		log.info("onAuthenticationSuccess");
        //      이동 할 URL 지정
        resultRedirectStrategy(request, response, auth);
    }

    private void resultRedirectStrategy(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(SESSION_TIME);
        log.info("resultRedirectStrategy Session Time {}", SESSION_TIME);

        if( authentication.getPrincipal() instanceof DefaultOAuth2User ){
            Map<String, Object> attributes = ((DefaultOAuth2User) authentication.getPrincipal()).getAttributes();


//            UserProfile userProfile = OAuthAttributes.extract(registrationId, attributes);
        }

//        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
//
//        // "USER"로 sessionVO를 세션에 바인딩한다.
//        session.setAttribute("USER", user);


        log.info("session:", session);

        String isAjaxLogin = request.getHeader("isAjaxLogin");
        if (isAjaxLogin != null && "true".equals(isAjaxLogin)) {
            log.info("========= AJAX LOGIN REQUEST!!!! =========");

            //ajax 로 호출된 경우 json 으로 결과를 리턴한다.
            ObjectMapper om = new ObjectMapper();
            ResponseDto rDto = new ResponseDto( MessageType.OK );
            rDto.setMessage( MessageUtil.getMessage(MessageType.OK.toString()) );
            rDto.setData( "SUCCESS" );

            response.setContentType("application/json;charset=UTF-8");

            String jsonString = om.writeValueAsString(rDto);
            OutputStream out = response.getOutputStream();
            out.write(jsonString.getBytes());

        }else {
            SavedRequest savedRequest = reqCache.getRequest(request, response);
            String targetUrl = null;
            if (savedRequest != null) {
                log.info("resultRedirectStrategy savedRequest != null ");
                targetUrl = savedRequest.getRedirectUrl();

                log.info("resultRedirectStrategy :{} ", targetUrl);
                // TODO : 권한별 로그인 Path 지정 추가 고려.
                //            Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
                //
                //            if( roles.contains( "ADMIN" ) ){
                //                targetUrl = "/home/admin";
                //            }
                //            else if( roles.contains( "USER" ) ){
                //                targetUrl = "/home/user";
                //            }
                //            else{
                //                targetUrl = "/home/other";
                //            }
            } else {
                log.info("resultRedirectStrategy savedRequest ");
                targetUrl = defaultUrl;
            }

            redirectStratgy.sendRedirect(request, response, targetUrl);
        }

    }

}

