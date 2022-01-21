package com.powernote.skeleton.powernoteboard.vo;

import com.powernote.skeleton.vo.PostDataVo;
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
public class PowerNotePostDataVo {

    private Long postNo = 0L;
    private String title = "";
    private String content = "";
    private int viewCnt = 0;

    private LocalDateTime regDate;
    private LocalDateTime uptDate;

    private String categoryName = "기본";
    private UserVo user;
}
