package com.powernote.skeleton.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@NoArgsConstructor
@Setter
@Getter
public class SocialUserVo {
    private long socialNo = -1L;
    private long userNo = -1L;
    private String socialType = "";
    private String oauthId = "";
    private String email = "";
    private String userName = "";
    private String nickName = "";
    private String profileImg = "/images/profile_default.png";
    private String telNumber = "";
    private LocalDateTime regDate;
    private LocalDateTime uptDate;

    public SocialUserVo update( String socialType, String oauthId, String email, String userName, String nickName, String profileImg, String telNumber) {
        this.socialType = socialType;
        this.oauthId = oauthId;
        this.email = email;
        this.userName = userName;
        this.nickName = nickName;
        this.profileImg = profileImg;
        this.telNumber = telNumber;
        return this;
    }
}