package com.powernote.skeleton.vo;

import com.powernote.skeleton.dto.SocialRegistDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@NoArgsConstructor
@Setter
@Getter
public class UserVo {
    private long userNo = -1L;
    private String userId = "";
    private String email = "";
    private String passwd = "";
    private String userName = "";
    private String nickName = "";
    private String profileImg = "/images/profile_default.png";
    private String telNumber = "";
    private String roles = "";
    private LocalDateTime regDate;
    private LocalDateTime uptDate;
    private LocalDateTime passwdresetDate;

    public UserVo socialToUser(SocialRegistDto socialRegistDto) {
        this.userId = socialRegistDto.getEmail();
        this.email = socialRegistDto.getEmail();
        this.passwd = socialRegistDto.getPasswd();
        this.userName = socialRegistDto.getUserName();
        this.nickName = socialRegistDto.getNickName();
        this.profileImg = socialRegistDto.getProfileImg();
        this.telNumber = socialRegistDto.getTelNumber();
        return this;
    }

}
