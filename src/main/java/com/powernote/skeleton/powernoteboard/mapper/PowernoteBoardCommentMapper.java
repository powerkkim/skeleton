package com.powernote.skeleton.powernoteboard.mapper;

import com.powernote.skeleton.powernoteboard.dto.PowerNoteCommentDataDto;
import com.powernote.skeleton.powernoteboard.vo.PowerNoteCommentDataVo;
import com.powernote.skeleton.powernoteboard.vo.PowerNotePostDataVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PowernoteBoardCommentMapper {

    void save(PowerNoteCommentDataDto commentData);

    PowerNoteCommentDataVo findCommentByCommentId(String commentNo);


    List<PowerNotePostDataVo> findByWord(Map<String, Object> mapperParam );
    int findByWordCount( Map<String, Object> mapperParam );

    List<PowerNoteCommentDataVo> findCommentByPostId(String postNo);

    long findCommentLastCommentGNoByPostId(String postNo);
}
