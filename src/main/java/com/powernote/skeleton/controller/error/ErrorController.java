package com.powernote.skeleton.controller.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
@RequestMapping("/error")
public class ErrorController {
    @RequestMapping("/error403")
    public String error403(HttpServletRequest request, Model model){

        log.info(" HomeController error403 {}", request.getAttribute("errMsg"));

        model.addAttribute("errMsg", request.getAttribute("errMsg"));
        return "error/403";
    }

    @RequestMapping("/error401")
    public String error401(HttpServletRequest request, Model model){

        log.info(" HomeController error401 {}", request.getAttribute("errMsg"));

        model.addAttribute("errMsg", request.getAttribute("errMsg"));
        return "error/401";
    }

}
