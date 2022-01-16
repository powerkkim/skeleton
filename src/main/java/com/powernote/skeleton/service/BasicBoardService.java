package com.powernote.skeleton.service;

import com.powernote.skeleton.dto.PageInfoDto;
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
    public void write(PostDataVo postDataVo) {
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();

        // 비로그인 접근
        if (authentication instanceof AnonymousAuthenticationToken) {
            throw new BaseException("", MessageType.ERROR_PAGE_403.toString(), HttpStatus.OK);
        }

        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

        log.info("authentication.getName(): "+ authentication.getName() );
        log.info("getUserNo: "+ user.getUserNo() );
        log.info("getEmail: "+ user.getEmail() );
        log.info("password: "+ user.getPassword() );

        log.info("username: "+ user.getUsername() );
        log.info("getNickname: "+ user.getNickname() );
        log.info("postDataVo: "+ postDataVo.toString());

//        postDataVo.

        if ( user.getUserNo() != postDataVo.getUserNo() ) {
            throw new BaseException("", MessageType.ERROR_LOGIN_001.toString(), HttpStatus.OK);
        }

        boardMapper.save(postDataVo);
    }

    public Page<PostDataVo> read( PageInfoDto rPageInfo ) {

        if( !StringUtils.hasLength(rPageInfo.getDirection())  ){
            rPageInfo.setStandard("board_no");
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

    public PostDataVo findByPostId(String boardNo) {
        PostDataVo postDataVo = boardMapper.findByPostId(boardNo);
        return postDataVo;
    }

    @Transactional
    public int update(PostDataVo postDataVo) {
        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();

        // 비로그인 접근
        if (authentication instanceof AnonymousAuthenticationToken) {
            throw new BaseException("", MessageType.ERROR_PAGE_403.toString(), HttpStatus.OK);
        }

        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

        log.info("authentication.getName(): "+ authentication.getName() );
        log.info("getUserNo: "+ user.getUserNo() );
        log.info("getEmail: "+ user.getEmail() );
        log.info("password: "+ user.getPassword() );

        log.info("username: "+ user.getUsername() );
        log.info("getNickname: "+ user.getNickname() );
        log.info("postDataVo: "+ postDataVo.toString());

        if ( user.getUserNo() != postDataVo.getUserNo() ) {
            throw new BaseException("", MessageType.ERROR_LOGIN_001.toString(), HttpStatus.OK);
        }

        int bResult = boardMapper.update(postDataVo);

        log.info("postDataVo: DB update" );
        return bResult;
    }

    public void delete(PostDataVo postDataVo) {
        boardMapper.delete(postDataVo);
    }

    @Transactional
    public int updateViewCnt( PostDataVo postDataVo ) {
        int bResult = boardMapper.updateViewCnt(postDataVo);
        return bResult;
    }
}
