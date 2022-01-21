package com.powernote.skeleton.powernoteboard.vo;

import com.powernote.skeleton.vo.UserVo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class PowerNoteCommentDataVo {
    private Long commentNo = 0L;  // PK
    private Long postNo = 0L;
    private Long commentGno = 0L;
    private Long commentGord = 0L;
    private Long commentDepth = 0L;
    private String content = "";
    private LocalDateTime regDate;
    private LocalDateTime uptDate;

    private UserVo parUser;
    private UserVo user;
}
