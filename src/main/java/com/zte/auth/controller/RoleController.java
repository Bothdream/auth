package com.zte.auth.controller;

import com.zte.auth.service.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
@Slf4j
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @PostMapping(value = "/roles")
    public void addRole(){

    }

    @PutMapping(value = "/roles")
    public void putRole(){

    }

    @DeleteMapping(value = "/roles")
    public void deleteRole(){

    }

    @GetMapping(value = "/roles")
    public void queryRole(){

    }
}
