package com.powernote.skeleton.powernoteboard.mapper;

import com.powernote.skeleton.powernoteboard.dto.PowerNotePostDataDto;
import com.powernote.skeleton.powernoteboard.vo.PowerNotePostDataVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PowernoteBoardMapper {

    void save(PowerNotePostDataDto postDataVo);

    List<PowerNotePostDataVo> findAll();

    int findAllCount();

    List<PowerNotePostDataVo> findByWord(Map<String, Object> mapperParam );

    int findByWordCount( Map<String, Object> mapperParam );

    PowerNotePostDataVo findByPostId(String postNo);

    int update(PowerNotePostDataDto postDataVo);

    void delete(PowerNotePostDataDto postDataVo);

    int updateViewCnt(String postNo);
}
