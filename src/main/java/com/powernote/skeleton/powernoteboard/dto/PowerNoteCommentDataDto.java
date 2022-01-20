package com.powernote.skeleton.powernoteboard.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class PowerNoteCommentDataDto {
    private Long commentNo = 0L;  // PK
    private Long postNo = 0L;
    private Long commentGno = 0L;
    private Long commentGord = 0L;
    private Long commentDepth = 0L;
    private Long parUserNo = 0L;
    private Long userNo = 0L;
    private String content = "";
}
