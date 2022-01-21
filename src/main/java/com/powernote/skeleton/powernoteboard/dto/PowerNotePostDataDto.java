package com.powernote.skeleton.powernoteboard.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class PowerNotePostDataDto {
    private Long postNo = 0L;
    private Long userNo = 0L;
    private String title = "";
    private String content = "";
    private String categoryName = "기본";
}
