package com.zte.auth.controller;

import com.zte.auth.service.IPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
    public void putPermission(){

    }

    @GetMapping(value = "/permissions")
    public void queryPermission(){

    }

    @DeleteMapping(value = "/permissions")
    public void deletePermission(){

    }
}
