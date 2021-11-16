package com.powernote.skeleton.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
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

@Slf4j
public class CustomLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private RequestCache reqCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStratgy = new DefaultRedirectStrategy();

    private String defaultUrl;

    private static int SESSION_TIME = 60 * 1; // 30 Minutes

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

    private void resultRedirectStrategy(HttpServletRequest req, HttpServletResponse res,
                                        Authentication authentication) throws IOException, ServletException {
        SavedRequest savedRequest = reqCache.getRequest(req, res);

        HttpSession session = req.getSession();
        session.setMaxInactiveInterval(SESSION_TIME);
        log.info("resultRedirectStrategy Session Time {}", SESSION_TIME);
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
        }
        else{
            log.info("resultRedirectStrategy savedRequest ");
            targetUrl = defaultUrl;
        }

        redirectStratgy.sendRedirect(req, res, targetUrl);

    }

}

