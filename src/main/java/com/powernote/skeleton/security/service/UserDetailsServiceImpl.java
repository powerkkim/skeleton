package com.powernote.skeleton.security.service;

//import com.workspringboot.demo06security.entity.Account;
//import com.workspringboot.demo06security.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

//    private final UserRepository userRepository;
//
//    UserDetailsServiceImpl(UserRepository userRepository ) {
//        this.userRepository = userRepository;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Account user = userRepository.findById(username);
//        UserDetails userDetail = User.builder().username(user.getUserName())
//                .password( user.getPassWord() )
//                .roles( user.getRoles() )
//                .build();
//
//        return userDetail;
        return User.builder().build();
    }
}
