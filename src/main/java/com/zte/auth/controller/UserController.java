package com.zte.auth.controller;

import com.zte.auth.aop.annotation.CheckRequestParameter;
import com.zte.auth.entity.User;
import com.zte.auth.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/api")
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping(value = "/users")
    public User getUser(){
        User user = new User();
        user.setId(123L);
        user.setName("zhangsan");
        user.setEnabledFlag("1");
        return user;
    }

    @PutMapping(value = "/users")
    public void putUser(){

    }

    @DeleteMapping(value = "/users")
    public void deleteUser(){

    }

    @PostMapping(value = "/users")
    @CheckRequestParameter
    public User addUser(@RequestBody @Valid User user){

        return user;
    }
}
