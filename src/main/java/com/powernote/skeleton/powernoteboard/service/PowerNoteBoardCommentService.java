package com.powernote.skeleton.powernoteboard.service;

import com.powernote.skeleton.powernoteboard.dto.PowerNoteCommentDataDto;
import com.powernote.skeleton.exception.basic.BaseException;
import com.powernote.skeleton.exception.error.MessageType;
import com.powernote.skeleton.powernoteboard.mapper.PowernoteBoardCommentMapper;
import com.powernote.skeleton.security.vo.CustomUserDetails;
import com.powernote.skeleton.powernoteboard.vo.PowerNoteCommentDataVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class PowerNoteBoardCommentService {

    @Autowired
    PowernoteBoardCommentMapper boardCommentMapper;

    @Transactional
    public PowerNoteCommentDataVo write(PowerNoteCommentDataDto commentData ) {
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
        log.info("postDataVo: "+ commentData.toString());

        if ( user.getUserNo() != commentData.getUserNo() ) {
            throw new BaseException("", MessageType.ERROR_LOGIN_001.toString(), HttpStatus.OK);
        }

        boardCommentMapper.save(commentData);
        log.info("commentDataVo: "+ commentData.toString());
        return boardCommentMapper.findCommentByCommentId( String.valueOf(commentData.getCommentNo()) );
    }
//
//    public Page<PowerNotePostDataVo> read(PageInfoDto rPageInfo ) {
//
//        if( !StringUtils.hasLength(rPageInfo.getDirection())  ){
//            rPageInfo.setStandard("post_no");
//        }
//        log.info(rPageInfo.toString());
//
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("pageSize", Integer.parseInt(rPageInfo.getSize()));
//        paramMap.put("offset", Integer.parseInt(rPageInfo.getNumber()) * Integer.parseInt(rPageInfo.getSize()));
//        paramMap.put("boardNo", 0);
//        paramMap.put("categoryName", "기본");
////        paramMap.put("searchKey", rPageInfo.getSearchKey());
////        paramMap.put("searchWord", rPageInfo.getSearchWord());
//
//        if ( StringUtils.hasLength(rPageInfo.getSearchKey() ) ) {
//            paramMap.put(rPageInfo.getSearchKey(), rPageInfo.getSearchWord() );
//        }
//
//        log.info( paramMap.toString() );
//        List<PowerNotePostDataVo> plist = boardMapper.findByWord(paramMap);
//        int nTotalCnt = boardMapper.findByWordCount(paramMap);
//
//        PageRequest pageRequest = PageRequest.of( Integer.parseInt( rPageInfo.getNumber() ),
//                                                    Integer.parseInt(rPageInfo.getSize() ),
//                                                    rPageInfo.getDirection().equals("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC,
//                                                    rPageInfo.getStandard()  );
//
//        Page<PowerNotePostDataVo> dtoPages = new PageImpl<PowerNotePostDataVo>(
//                plist,
//                pageRequest,
//                nTotalCnt );
//
//        return dtoPages;
//    }
//
//    public PowerNotePostDataVo findByPostId(String postNo) {
//        PowerNotePostDataVo postDataVo = boardMapper.findByPostId(postNo);
//        return postDataVo;
//    }
//
//    @Transactional
//    public int update(PowerNotePostDataVo postDataVo) {
//        Authentication authentication =  SecurityContextHolder.getContext().getAuthentication();
//
//        // 비로그인 접근
//        if (authentication instanceof AnonymousAuthenticationToken) {
//            throw new BaseException("", MessageType.ERROR_PAGE_403.toString(), HttpStatus.OK);
//        }
//
//        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
//
//        log.info("authentication.getName(): "+ authentication.getName() );
//        log.info("getUserNo: "+ user.getUserNo() );
//        log.info("getEmail: "+ user.getEmail() );
//        log.info("password: "+ user.getPassword() );
//
//        log.info("username: "+ user.getUsername() );
//        log.info("getNickname: "+ user.getNickname() );
//        log.info("postDataVo: "+ postDataVo.toString());
//
//        if ( user.getUserNo() != postDataVo.getUserNo() ) {
//            throw new BaseException("", MessageType.ERROR_LOGIN_001.toString(), HttpStatus.OK);
//        }
//
//        int bResult = boardMapper.update(postDataVo);
//
//        log.info("postDataVo: DB update" );
//        return bResult;
//    }
//
//    public void delete(PowerNotePostDataVo postDataVo) {
//        boardMapper.delete(postDataVo);
//    }
//
//    @Transactional
//    public int updateViewCnt( String postNo ) {
//        int bResult = boardMapper.updateViewCnt(postNo);
//        return bResult;
//    }
//
    public List<PowerNoteCommentDataVo> findCommentByPostId(String postNo) {
        return boardCommentMapper.findCommentByPostId(postNo);
    }
}
