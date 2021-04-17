package com.zte.auth.controller;

import com.zte.auth.common.ServiceData;
import com.zte.auth.entity.De;
import com.zte.auth.entity.User;
import com.zte.auth.service.IUserService;
import com.zte.auth.utils.DateUtil;
import com.zte.auth.utils.ExeclUtil;
import com.zte.auth.utils.I18nUtils;
import com.zte.auth.utils.dto.ExcelTableExportParams;
import com.github.jsonzou.jmockdata.JMockData;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Api(value = "用户信息",tags = "用户信息相关API")
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private IUserService userService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping(value = "/add")
    @ResponseBody
    public ServiceData<List<User>> addUser(){
        String msg = I18nUtils.get("user.title");
        String mg = I18nUtils.get("gh", new String[]{"123", "123"});
        System.out.println(msg);
        System.out.println(mg);
        ServiceData<List<User>> data = new ServiceData<>();
        List<User> users = userService.listUser();
        data.setBo(users);
        return data;
    }

    @PutMapping(value = "/delete")
    @ResponseBody
    public void deleteUser(){

    }

    @PutMapping(value = "/update")
    @ResponseBody
    public void updateUser(){

    }


    @GetMapping(value = "/upload/file")
    @ResponseBody
    public void uploadFile(HttpServletResponse response) throws UnsupportedEncodingException {

        List<User> lists = new ArrayList<>();
        for (int i = 0;i < 10000;i++) {
            User g = JMockData.mock(User.class);
            lists.add(g);
        }
        ExcelTableExportParams excelTableExportParams = new ExcelTableExportParams(lists,User.class);
        List<De> lists1 = new ArrayList<>();
        for (int i = 0;i < 10000;i++) {
            De t = new De();
            t.setUserName("zhangsan" + i);
            t.setUserId((long)i);
            t.setEnabledFlag("Y");
            t.setCreationDate(DateUtil.date2TimeStr(new Date(),DateUtil.MINUTE));
            t.setLastUpdatedDate(DateUtil.date2TimeStr(new Date(),DateUtil.MINUTE));
            lists1.add(t);
        }
        ExcelTableExportParams excelTableExportParams1 = new ExcelTableExportParams(lists1,De.class);
        List<ExcelTableExportParams> s = new ArrayList();
        s.add(excelTableExportParams);
        s.add(excelTableExportParams1);
        ExeclUtil.exportSheets(s,response);
    }
}
