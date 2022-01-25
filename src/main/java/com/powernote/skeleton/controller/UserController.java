package com.powernote.skeleton.controller;

import com.powernote.skeleton.service.UserService;
import com.powernote.skeleton.vo.SocialUserVo;
import com.powernote.skeleton.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import oracle.ucp.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public String login() { return "pages/login"; }

    @GetMapping("/regist")
    public String userJoin() {
        return "pages/user_regist";
    }

//    /**
//     * 파라미터 숨기기용도
//     * @param redirectAttributes
//     * @param msg
//     * @return
//     */
//    @RequestMapping("/social_preregist")
//    public String socialPreRegist(RedirectAttributes redirectAttributes, SocialUserVo socialUserVo) {
//
//        redirectAttributes.addFlashAttribute("socialUser", socialUserVo);
//        log.info("aaa errMsg: {}" , socialUserVo);
//
//        return "redirect:/user/social_user_regist";
//    }

    @GetMapping("/social_user_regist")
    public String socialRegist( @ModelAttribute("socialUser") SocialUserVo socialUser ) {

        log.info("bbb socialUserVo: {}" , socialUser);

        return "pages/social_user_regist";
    }

    @GetMapping("/profile")
    public String userProfile( Authentication authentication ) {

        log.info( authentication.toString() );
        log.info( authentication.getPrincipal().toString() );

        if( authentication.getPrincipal() instanceof DefaultOAuth2User ) {
            log.info( "DefaultOAuth2User principal" );
            DefaultOAuth2User oAuth2User  = (DefaultOAuth2User)authentication.getPrincipal();
            oAuth2User.getAuthorities();
        }


        return "pages/user_profile";
    }

    @GetMapping("/duplicated-login")
    public String duplicated() {
        return "pages/duplicated-login";
    }
}
