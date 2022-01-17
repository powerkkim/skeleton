package com.powernote.skeleton.mapper;

import com.powernote.skeleton.vo.PostDataVo;
import com.powernote.skeleton.vo.PowerNotePostDataVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface PowernoteBoardMapper {

    void save(PowerNotePostDataVo postDataVo);

    List<PowerNotePostDataVo> findAll();

    int findAllCount();

    List<PowerNotePostDataVo> findByWord(Map<String, Object> mapperParam );

    int findByWordCount( Map<String, Object> mapperParam );

    PowerNotePostDataVo findByPostId(String postNo);

    int update(PowerNotePostDataVo postDataVo);

    void delete(PowerNotePostDataVo postDataVo);

    int updateViewCnt(String postNo);
}
