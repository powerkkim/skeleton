package com.powernote.skeleton.service;

import com.powernote.skeleton.dto.SocialRegistDto;
import com.powernote.skeleton.mapper.SocialUserMapper;
import com.powernote.skeleton.mapper.UserMapper;
import com.powernote.skeleton.security.UserRoleE;
import com.powernote.skeleton.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    SocialUserMapper socialUserMapper;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public int insertUser(UserVo user) {
        // 암호화 처리 저장.
        user.setPasswd( passwordEncoder.encode(user.getPasswd()) );
        user.setRoles( UserRoleE.ROLE_USER.toString() );

        int nInsert = userMapper.insertUser( user );

        return nInsert;
    }

    @Transactional
    public int insertUserFromSocial(SocialRegistDto socialUser) {

        UserVo user = new UserVo();
        user.socialToUser(socialUser);

        // 암호화 처리 저장.
        user.setPasswd( passwordEncoder.encode(user.getPasswd()) );
        user.setRoles( UserRoleE.ROLE_USER.toString() );

        int nInsert = userMapper.insertUser( user );

        if( nInsert > 0 ) {
            return socialUserMapper.updateUserNo(socialUser.getSocialType(), socialUser.getOauthId(), String.valueOf(user.getUserNo()) );
        }

        return -1;
    }
}
