package com.powernote.skeleton.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class PostDataDto {
    private long postNo = 0L;
    private long userNo = 0L;
    private String title = "";
    private String content = "";
}
