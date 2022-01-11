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
public class UserVo {
    private Long userNo = 0L;
    private String email = "";
    private String passwd = "";
    private String userName = "";
    private String nickName = "";
    private String telNumber = "";
    private String roles = "";
    private LocalDateTime regDate;
    private LocalDateTime uptDate;
    private LocalDateTime passwdresetDate;

}
