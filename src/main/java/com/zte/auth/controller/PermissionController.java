package com.zte.auth.controller;

import com.zte.auth.service.IPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
@Slf4j
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;

    @PostMapping(value = "/permissions")
    public void addPermission(){

    }

    @PutMapping(value = "/permissions")
    public void deletePermission(){

    }

    @DeleteMapping(value = "/permissions")
    public void updatePermission(){

    }

    @GetMapping(value = "/permissions")
    public void queryPermission(){

    }
}
