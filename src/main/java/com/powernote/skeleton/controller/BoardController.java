package com.powernote.skeleton.controller;

import com.powernote.skeleton.dto.PageInfoDto;
import com.powernote.skeleton.service.BoardService;
import com.powernote.skeleton.vo.PostDataVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {


    @Autowired
    BoardService boardService;

    @GetMapping(value = "/main")
    public String board(Model model, PageInfoDto rPageInfo) {
        Page<PostDataVo> dtoPages  = boardService.read(rPageInfo);
        model.addAttribute("pagesnav", dtoPages);

        return "pages/board";
    }

    @GetMapping(value = "/writeform")
    public String writeform() { return "pages/board_write_form"; }

}
