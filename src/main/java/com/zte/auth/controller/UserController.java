package com.zte.auth.controller;

import com.github.jsonzou.jmockdata.JMockData;
import com.zte.auth.aop.annotation.CheckRequestParameter;
import com.zte.auth.dao.auth.UserDao;
import com.zte.auth.dao.order.OrderDao;
import com.zte.auth.entity.Order;
import com.zte.auth.entity.User;
import com.zte.auth.event.MyEvent;
import com.zte.auth.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.validation.Valid;
import java.util.Date;


@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ApplicationContext applicationContext;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping(value = "/users")
    public User getUser(ServletRequest servletRequest){
        User user = new User();
        user.setId(123L);
        user.setName("zhangsan");
        user.setEnabledFlag("1");
        servletRequest.setAttribute("123",123);
        servletRequest.removeAttribute("123");
        applicationContext.publishEvent(new MyEvent("1234567"));
        return user;
    }

    @PutMapping(value = "/users")
    public void deleteUser(){

    }

    @Autowired
    private UserDao userDao;

    @Autowired
    private OrderDao orderDao;

    @DeleteMapping(value = "/users")
    public void updateUser(){
        User user = JMockData.mock(User.class);
        Order order = JMockData.mock(Order.class);
        order.setCreateTime(new Date());
        userDao.insert(user);
        orderDao.insertOrder(order);
    }

    @PostMapping(value = "/users")
    @CheckRequestParameter
    public User addUser(@RequestBody @Valid User user){

        return user;
    }
}
