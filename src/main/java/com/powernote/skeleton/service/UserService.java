package com.powernote.skeleton.service;

import com.powernote.skeleton.mapper.UserMapper;
import com.powernote.skeleton.security.UserRoleE;
import com.powernote.skeleton.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public int insertUser(UserVo user) {
        // 암호화 처리 저장.
        user.setPasswd( passwordEncoder.encode(user.getPasswd()) );
        user.setRoles( UserRoleE.ROLE_USER.toString() );

        int nInsert = userMapper.insertUser( user );

        return nInsert;
    }
}
