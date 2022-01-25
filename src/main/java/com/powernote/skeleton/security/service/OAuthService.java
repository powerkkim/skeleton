package com.powernote.skeleton.security.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.powernote.skeleton.exception.basic.SocialLoginProccessException;
import com.powernote.skeleton.mapper.SocialUserMapper;
import com.powernote.skeleton.mapper.UserMapper;
import com.powernote.skeleton.security.vo.CustomUserDetails;
import com.powernote.skeleton.security.vo.OAuthAttributes;
import com.powernote.skeleton.vo.SocialUserVo;
import com.powernote.skeleton.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class OAuthService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    UserMapper userMapper;
    SocialUserMapper socialUserMapper;

    public OAuthService(UserMapper userMapper, SocialUserMapper socialUserMapper) {
        this.userMapper = userMapper;
        this.socialUserMapper = socialUserMapper;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest); // OAuth 서비스(github, google, naver)에서 가져온 유저 정보를 담고있음

        String registrationId = userRequest.getClientRegistration().getRegistrationId(); // OAuth 서비스 이름(ex. github, naver, google)
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName(); // OAuth 로그인 시 키(pk)가 되는 값
        Map<String, Object> attributes = oAuth2User.getAttributes(); // OAuth 서비스의 유저 정보들

        SocialUserProfile socialUserProfile = OAuthAttributes.extract(registrationId, attributes); // registrationId에 따라 유저 정보를 통해 공통된 UserProfile 객체로 만들어 줌

        SocialUserVo socialUserVo = socialUserMapper.findById(socialUserProfile.getSocialType(), socialUserProfile.getOauthId());
        if( socialUserVo == null ) {
            socialUserVo = new SocialUserVo();
            socialUserVo.setSocialType(socialUserProfile.getSocialType());
            socialUserVo.setOauthId(socialUserProfile.getOauthId() );
            socialUserVo.setEmail( socialUserProfile.getEmail() );
            socialUserVo.setUserName( socialUserProfile.getName() );
            socialUserVo.setNickName( "" );
            socialUserVo.setProfileImg( socialUserProfile.getImageUrl() );
            socialUserVo.setTelNumber( "");

            socialUserMapper.insert( socialUserVo );
        }
        else {
            socialUserVo.update(socialUserProfile.getSocialType(),
                    socialUserProfile.getOauthId(),
                    socialUserProfile.getEmail(),
                    socialUserProfile.getName(),
                    "",
                    socialUserProfile.getImageUrl(),
                    "");
            socialUserMapper.update( socialUserVo );
        }

        log.info( socialUserVo.toString() );

        if( socialUserVo.getUserNo() <= 0L ) {
            throw new SocialLoginProccessException("UserNotFound" , socialUserVo);
        }

        UserVo user = userMapper.findByUserNo( socialUserVo.getUserNo() );
        if( user == null ) {
            throw new SocialLoginProccessException("UserNotFound" , socialUserVo);
        }

        CustomUserDetails defaultOAuth2User = Optional.ofNullable(user)
                .map((data)->{
                    log.info( "User 정보 :{}" , user.toString() );

                    ObjectMapper om = new ObjectMapper();
                    Map<String, String> map = om.convertValue(socialUserProfile, Map.class);

                    return new CustomUserDetails( user, attributes );
                })
                .orElseGet(()-> {
                    log.info("Login 정보 오류 ");
                    throw new UsernameNotFoundException("UserNotFound");
                });

        return defaultOAuth2User;
    }

}
