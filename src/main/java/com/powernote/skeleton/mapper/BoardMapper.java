package com.powernote.skeleton.mapper;

import com.powernote.skeleton.dto.PageInfoDto;
import com.powernote.skeleton.vo.PostDataVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {

    void save(PostDataVo postDataVo);

    List<PostDataVo> findAll();

    int findAllCount();

    List<PostDataVo> findByWord(  Map<String, Object> mapperParam );

    int findByWordCount( Map<String, Object> mapperParam );

    PostDataVo findByPostId(String postNo);

    int update(PostDataVo postDataVo);

    void delete(PostDataVo postDataVo);

    int updateViewCnt(String postNo);
}
