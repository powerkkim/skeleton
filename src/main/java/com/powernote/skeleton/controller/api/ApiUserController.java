package com.powernote.skeleton.controller.api;

import com.powernote.skeleton.dto.ResponseDto;
import com.powernote.skeleton.dto.SocialRegistDto;
import com.powernote.skeleton.exception.error.MessageType;
import com.powernote.skeleton.service.UserService;
import com.powernote.skeleton.util.CommonUtil;
import com.powernote.skeleton.vo.SocialUserVo;
import com.powernote.skeleton.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api")
public class ApiUserController {

    @Autowired
    UserService userService;

    @PostMapping("/user_post_regist")
    public ResponseEntity<ResponseDto<String>> userRegist(UserVo user) {
        log.info( user.toString() );
        ResponseDto<String> responseDto = new ResponseDto<>();

        if( userService.insertUser(user) > 0 ) {
            responseDto.setMessage(MessageType.OK.toString());
            responseDto.setResult(MessageType.OK.getMessage());

            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }

        responseDto.setMessage(MessageType.NOK.toString());
        responseDto.setResult(MessageType.NOK.getMessage());
        return  new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/user_post_socialregist")
    public ResponseEntity<ResponseDto<String>> socialuserRegist( SocialRegistDto social ) {
        ResponseDto<String> responseDto = new ResponseDto<>();

        if( userService.insertUserFromSocial(social) > 0 ) {
            responseDto.setMessage(MessageType.OK.toString());
            responseDto.setResult(MessageType.OK.getMessage());

            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }

        responseDto.setMessage(MessageType.NOK.toString());
        responseDto.setResult(MessageType.NOK.getMessage());
        return  new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
}
