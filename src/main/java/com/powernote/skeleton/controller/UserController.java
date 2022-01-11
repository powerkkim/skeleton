package com.powernote.skeleton.controller;

import com.powernote.skeleton.service.UserService;
import com.powernote.skeleton.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import oracle.ucp.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public String login() { return "pages/login"; }

    @GetMapping("/regist")
    public String userJoin() {
        return "pages/user_regist";
    }

    @GetMapping("/profile")
    public String userProfile() {
        return "pages/user_profile";
    }
}
