package com.powernote.skeleton.controller;

import com.powernote.skeleton.dto.PageInfoDto;
import com.powernote.skeleton.service.BasicBoardService;
import com.powernote.skeleton.vo.PostDataVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequestMapping("/board")
public class BasicBoardController {

    @Autowired
    BasicBoardService boardService;

    @GetMapping(value = "/main")
    public String board(Model model, PageInfoDto rPageInfo) {
        Page<PostDataVo> dtoPages  = boardService.read(rPageInfo);
        model.addAttribute("pagesnav", dtoPages);
        model.addAttribute("pageInfo", rPageInfo);
        return "pages/basicboard";
    }

    @GetMapping(value = "/view")
    public String board(Model model, PageInfoDto rPageInfo, @RequestParam(value = "boardNo") String boardNo ) {
        PostDataVo postDataVo = boardService.findByPostId(boardNo);
        model.addAttribute("postData", postDataVo);
        model.addAttribute("pageInfo", rPageInfo);
        return "pages/basicboard_view";
    }

    @GetMapping(value = "/edit")
    public String edit(Model model, @RequestParam(value = "boardNo") String boardNo ) {

        PostDataVo postDataVo = boardService.findByPostId(boardNo);
        model.addAttribute("postData", postDataVo);

        return "pages/basicboard_editor_form";
    }

    @GetMapping(value = "/writeform")
    public String writeform() { return "pages/basicboard_write_form"; }

}
