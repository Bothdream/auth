package com.zte.auth.controller;

import com.github.jsonzou.jmockdata.JMockData;
import com.zte.auth.aop.annotation.CheckRequestParameter;
import com.zte.auth.dao.auth.UserDao;
import com.zte.auth.dao.order.OrderDao;
import com.zte.auth.entity.Order;
import com.zte.auth.entity.User;
import com.zte.auth.event.CustomEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping("/api")
@Slf4j
public class TestController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping(value = "/tests")
    public void getUser(ServletRequest servletRequest){
        servletRequest.setAttribute("123",123);
        servletRequest.removeAttribute("123");
        applicationContext.publishEvent(new CustomEvent("1234567"));
    }

    @DeleteMapping(value = "/tests")
    public void updateUser(){
        User user = JMockData.mock(User.class);
        Order order = JMockData.mock(Order.class);
        order.setCreateTime(new Date());
        userDao.insert(user);
        orderDao.insertOrder(order);
    }

    @PostMapping(value = "/tests")
    @CheckRequestParameter
    public User addUser(@RequestBody @Valid User user){

        return user;
    }
}

