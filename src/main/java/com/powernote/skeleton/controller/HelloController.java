package com.powernote.skeleton.controller;

import com.powernote.skeleton.mapper.TestMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@Slf4j
public class HelloController {

    @Autowired
    TestMapper testMapper;


    @GetMapping("/hello")
    public String Hello(){

        String st = testMapper.selectTest();
        System.out.println("$$$$$$$$$$$$" + st);
        return "pages/hello";
    }
}
