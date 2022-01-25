package com.powernote.skeleton.mapper;

import com.powernote.skeleton.vo.SocialUserVo;
import com.powernote.skeleton.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

@Mapper
public interface SocialUserMapper {

    SocialUserVo  findById(@Param("socialType") String socialType, @Param("oauthId") String oauthId);

    int insert(SocialUserVo item);

    int update(SocialUserVo socialUserVo);

    int updateUserNo(String socialType, String oauthId, String userNo);
}

