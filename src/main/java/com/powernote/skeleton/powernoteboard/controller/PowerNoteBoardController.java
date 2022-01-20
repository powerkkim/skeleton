package com.powernote.skeleton.powernoteboard.controller;

import com.powernote.skeleton.dto.PageInfoDto;
import com.powernote.skeleton.powernoteboard.service.PowerNoteBoardCommentService;
import com.powernote.skeleton.powernoteboard.service.PowerNoteBoardService;
import com.powernote.skeleton.powernoteboard.vo.PowerNoteCommentDataVo;
import com.powernote.skeleton.powernoteboard.vo.PowerNotePostDataVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/powernoteboard")
public class PowerNoteBoardController {

    @Autowired
    PowerNoteBoardService boardService;

    @Autowired
    PowerNoteBoardCommentService boardCommentService;

    @GetMapping(value = "/main")
    public String board(Model model, PageInfoDto rPageInfo) {
        Page<PowerNotePostDataVo> dtoPages  = boardService.read(rPageInfo);
        model.addAttribute("pagesnav", dtoPages);
        model.addAttribute("pageInfo", rPageInfo);
        return "pages/powernoteboard/powernoteboard";
    }

    @GetMapping(value = "/view")
    public String board(HttpServletRequest request, HttpServletResponse response, Model model, PageInfoDto rPageInfo, @RequestParam(value = "postNo") String postNo ) {
        // 저장된 쿠키 불러오기
        Cookie cookies[] = request.getCookies();

        Map mapCookie = new HashMap();
        if(request.getCookies() != null){
            for (int i = 0; i < cookies.length; i++) {
                Cookie obj = cookies[i];
                mapCookie.put(obj.getName(),obj.getValue());
            }
        }

        // 저장된 쿠키중에 read_count 만 불러오기
        String cookie_read_count = StringUtils.hasLength((String) mapCookie.get("read_count")) ? (String) mapCookie.get("read_count") : "";
        // 저장될 새로운 쿠키값 생성
        String new_cookie_read_count = "|" + postNo;
        StringUtils.hasLength(null);

        // 저장된 쿠키에 새로운 쿠키값이 존재하는 지 검사
        if ( StringUtils.countOccurrencesOf(cookie_read_count, new_cookie_read_count) <= 0 ) {
            // 없을 경우 쿠키 생성
            Cookie cookie = new Cookie("read_count", cookie_read_count + new_cookie_read_count);
            cookie.setMaxAge(86400); // 초단위  하루 60초*60분*24시 = 86400
            response.addCookie(cookie);

            int nUpCnt = boardService.updateViewCnt(postNo);
            if( nUpCnt <= 0 ) log.info( "updateViewCnt no update: {}", nUpCnt );
        }

        PowerNotePostDataVo postDataVo = boardService.findByPostId(postNo);
        List<PowerNoteCommentDataVo> commentList = boardCommentService.findCommentByPostId(postNo);

        model.addAttribute("postData", postDataVo);
        model.addAttribute("pageInfo", rPageInfo);
        model.addAttribute("commentList", commentList);

        return "pages/powernoteboard/powernoteboard_view";
    }

    @GetMapping(value = "/edit")
    public String edit(Model model, @RequestParam(value = "postNo") String postNo ) {

        PowerNotePostDataVo postDataVo = boardService.findByPostId(postNo);
        model.addAttribute("postData", postDataVo);

        return "pages/powernoteboard/powernoteboard_editor_form";
    }

    @GetMapping(value = "/writeform")
    public String writeform() { return "pages/powernoteboard/powernoteboard_write_form"; }

}
