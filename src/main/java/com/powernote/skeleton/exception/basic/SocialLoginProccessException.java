package com.powernote.skeleton.exception.basic;

import com.powernote.skeleton.vo.SocialUserVo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;

@Setter
@Getter
public class SocialLoginProccessException extends OAuth2AuthenticationException {

    private SocialUserVo socialUserVo;

    public SocialLoginProccessException(String errorCode) {
        super(errorCode);
    }

    public SocialLoginProccessException(String errorCode, SocialUserVo socialUserVo) {
        super(errorCode);
        this.socialUserVo = socialUserVo;
    }

}
