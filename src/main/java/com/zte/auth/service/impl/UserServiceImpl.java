package com.zte.auth.service.impl;

import com.zte.auth.entity.User;
import com.zte.auth.dao.auth.UserDao;
import com.zte.auth.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> listUser(){
      User user = new User();
      user.setId(2L);
      user.setName("李四");
      return  userDao.listUser(user);
    }
}
