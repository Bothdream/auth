package com.zte.auth.dao;

import com.zte.auth.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    List<User> listUser(User user);

    int insert(@Param("username") String username);
}
