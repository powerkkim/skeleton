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
public class PowerNotePostDataVo extends PostDataVo {

    private String userName = "";
    private String email = "";
    private int boardNo = 0;
    private String categoryName = "기본";
}
