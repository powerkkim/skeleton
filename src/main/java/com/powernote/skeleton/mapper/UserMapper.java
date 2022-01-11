package com.powernote.skeleton.mapper;

import com.powernote.skeleton.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    int insertUser(UserVo user);

    UserVo findById(String Id);

}

