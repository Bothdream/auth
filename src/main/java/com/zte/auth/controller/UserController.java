package com.zte.auth.controller;

import com.zte.auth.entity.User;
import com.zte.auth.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    private IUserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping(value = "/users")
    public User getUser(){
        User user = new User();
        user.setId(123L);
        user.setName("zhangsan");
        user.setEnabledFlag("1");
        userService.listUser();
        return user;
    }

    @PutMapping(value = "/users")
    public void deleteUser(){

    }

    @DeleteMapping(value = "/users")
    public void updateUser(){

    }

    @PostMapping(value = "/users")
    public User addUser(@RequestBody @Valid User user){

        return user;
    }
}
