package com.zte.auth.controller;

import com.zte.auth.service.IPermissionService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "权限信息",tags = "权限信息相关API")
@RestController
@RequestMapping(value = "/Permission")
@Slf4j
public class PermissionController {
    @Autowired
    private IPermissionService permissionService;

    @ResponseBody
    @PostMapping(value = "/add")
    public void addPermission(){

    }

    @PutMapping(value = "/delete")
    @ResponseBody
    public void deletePermission(){

    }

    @PutMapping(value = "/update")
    @ResponseBody
    public void updatePermission(){

    }
}