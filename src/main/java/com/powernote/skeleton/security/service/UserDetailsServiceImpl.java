package com.powernote.skeleton.security.service;

//import com.workspringboot.demo06security.entity.Account;
//import com.workspringboot.demo06security.repository.UserRepository;
import com.powernote.skeleton.mapper.UserMapper;
import com.powernote.skeleton.security.vo.CustomUserDetails;
import com.powernote.skeleton.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.*;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserMapper userMapper;

//    public enum UserRoles {ROLE_USER, ROLE_ADMIN, ROLE_MASTER}
//
//    public static final SimpleGrantedAuthority ROLE_ADMIN_AUTH = new SimpleGrantedAuthority(UserRoles.ROLE_ADMIN.toString());
//    public static final SimpleGrantedAuthority ROLE_ANONYMOUS_AUTH = new SimpleGrantedAuthority("ROLE_ANONYMOUS");

    @Override
    public CustomUserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        log.info( "loadUserByUsername {}" , userId );

        UserVo user = userMapper.findById(userId);
        CustomUserDetails userDetails = Optional.ofNullable( user )
                .map((data)->{
                    log.info( "User 정보 :{}" , user.toString() );
                    CustomUserDetails detail = new CustomUserDetails( user );
                    return detail;
                })
                .orElseThrow(() -> new UsernameNotFoundException("UserNotFound"));

        return userDetails;
    }
}
