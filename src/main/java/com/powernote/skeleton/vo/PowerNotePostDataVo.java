package com.powernote.skeleton.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class PowerNotePostDataVo extends PostDataVo{
    private int boardNo = 0;
    private String categoryName = "기본";
}
