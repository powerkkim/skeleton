package com.powernote.skeleton.service;

import com.powernote.skeleton.dto.PageInfoDto;
import com.powernote.skeleton.dto.PostDataDto;
import com.powernote.skeleton.exception.basic.BaseException;
import com.powernote.skeleton.exception.error.MessageType;
import com.powernote.skeleton.mapper.BoardMapper;
import com.powernote.skeleton.security.vo.CustomUserDetails;
import com.powernote.skeleton.vo.PostDataVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class BasicBoardService {

    @Autowired
    BoardMapper boardMapper;

    @Transactional
    public void write(PostDataDto postData) {
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();

        // 비로그인 접근
        if (authentication instanceof AnonymousAuthenticationToken) {
            throw new BaseException("", MessageType.ERROR_PAGE_403.toString(), HttpStatus.OK);
        }

        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

        log.info("authentication.getName(): "+ authentication.getName() );
        log.info("getEmail: "+ user.getUsername() );
        log.info("password: "+ user.getPassword() );

        log.info("getUserNo: "+ user.getUserNo() );
        log.info("username: "+ user.getName() );
        log.info("getNickname: "+ user.getNickname() );
        log.info("postDataVo: "+ postData.toString());

//        postDataVo.

        if ( user.getUserNo() != postData.getUserNo() ) {
            throw new BaseException("", MessageType.ERROR_LOGIN_001.toString(), HttpStatus.OK);
        }

        boardMapper.save(postData);
    }

    public Page<PostDataVo> read( PageInfoDto rPageInfo ) {

        if( !StringUtils.hasLength(rPageInfo.getDirection())  ){
            rPageInfo.setStandard("post_no");
        }
        log.info(rPageInfo.toString());

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("pageSize", Integer.parseInt(rPageInfo.getSize()));
        paramMap.put("offset", Integer.parseInt(rPageInfo.getNumber()) * Integer.parseInt(rPageInfo.getSize()));
//        paramMap.put("searchKey", rPageInfo.getSearchKey());
//        paramMap.put("searchWord", rPageInfo.getSearchWord());
        if ( StringUtils.hasLength(rPageInfo.getSearchKey() ) ) {
            paramMap.put(rPageInfo.getSearchKey(), rPageInfo.getSearchWord() );
        }

        log.info( paramMap.toString() );
        List<PostDataVo> plist = boardMapper.findByWord(paramMap);
        int nTotalCnt = boardMapper.findByWordCount(paramMap);

        PageRequest pageRequest = PageRequest.of( Integer.parseInt( rPageInfo.getNumber() ),
                                                    Integer.parseInt(rPageInfo.getSize() ),
                                                    rPageInfo.getDirection().equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC,
                                                    rPageInfo.getStandard()  );

        Page<PostDataVo> dtoPages = new PageImpl<PostDataVo>(
                plist,
                pageRequest,
                nTotalCnt );

        return dtoPages;
    }

    public PostDataVo findByPostId(String postNo) {
        PostDataVo postDataVo = boardMapper.findByPostId(postNo);
        return postDataVo;
    }

    @Transactional
    public int update(PostDataDto postData) {
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();

        // 비로그인 접근
        if (authentication instanceof AnonymousAuthenticationToken) {
            throw new BaseException("", MessageType.ERROR_PAGE_403.toString(), HttpStatus.OK);
        }

        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

        log.info("authentication.getName(): "+ authentication.getName() );
        log.info("getEmail: "+ user.getUsername() );
        log.info("password: "+ user.getPassword() );

        log.info("getUserNo: "+ user.getUserNo() );
        log.info("username: "+ user.getName() );
        log.info("getNickname: "+ user.getNickname() );
        log.info("postDataVo: "+ postData.toString());

        if ( user.getUserNo() != postData.getUserNo() ) {
            throw new BaseException("", MessageType.ERROR_LOGIN_001.toString(), HttpStatus.OK);
        }

        int bResult = boardMapper.update(postData);

        log.info("postDataVo: DB update" );
        return bResult;
    }

    public void delete(PostDataDto postData) {
        boardMapper.delete(postData);
    }

    @Transactional
    public int updateViewCnt( String postNo ) {
        int bResult = boardMapper.updateViewCnt(postNo);
        return bResult;
    }
}
