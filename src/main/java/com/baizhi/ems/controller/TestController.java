package com.baizhi.ems.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("test")
    public String test(){
        System.out.println("test method invoke!!");
        return "test method ok";
    }
}
