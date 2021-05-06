package com.zte.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@Slf4j
public class SecurityController {

    @GetMapping(value = "")
    public String showOrder1(){
        return "index";
    }

    @GetMapping(value = "syslog")
    public String showOrder(){ return "syslog"; }

    @GetMapping(value = "sysuser")
    public String addOrder(){
        return "sysuser";
    }

    @GetMapping(value = "biz1")
    public String updateOrder(){
        return "biz1";
    }

    @GetMapping(value = "biz2")
    public String deleteOrder(){
        return "biz2";
    }
}
