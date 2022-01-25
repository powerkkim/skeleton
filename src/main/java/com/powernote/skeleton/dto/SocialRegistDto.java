package com.powernote.skeleton.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class SocialRegistDto {
    private Long socialNo = 0L;
    private String socialType = "";
    private String oauthId = "";
    private String email = "";
    private String userName = "";
    private String nickName = "";
    private String profileImg = "/images/profile_default.png";
    private String telNumber = "";
    private String passwd = "";
}
