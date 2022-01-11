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
public class PostDataVo {
    private Long boardNo = 0L;
    private String writer = "";
    private Long userNo = 0L;
    private String title = "";
    private String content = "";
    private LocalDateTime regDate;
    private LocalDateTime uptDate;
}