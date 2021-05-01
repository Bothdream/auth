package com.zte.auth.controller;

import com.zte.auth.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class RoleController {
    @Autowired
    private IRoleService roleService;

    @PostMapping(value = "/roles")
    public void addRole(){

    }


    @PutMapping(value = "/roles")
    public void deleteRole(){

    }

    @DeleteMapping(value = "/roles")
    public void updateRole(){

    }

    @GetMapping(value = "/roles")
    public void queryRole(){

    }
}
