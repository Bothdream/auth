package com.zte.auth.controller;

import com.zte.auth.service.IRoleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Api(value = "角色信息",tags = "角色信息相关API")
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @PostMapping(value = "/add")
    @ResponseBody
    public void addRole(){

    }


    @PutMapping(value = "/delete")
    @ResponseBody
    public void deleteRole(){

    }

    @PutMapping(value = "/update")
    @ResponseBody
    public void updateRole(){

    }
}
