package com.zte.auth.service.impl;

import com.zte.auth.entity.User;
import com.zte.auth.dao.UserDao;
import com.zte.auth.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;

    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public  List<User> listUser(){
      User user = new User();
      user.setId(2L);
      user.setName("李四");
      return  userDao.listUser(user);
    }

    private void zsetTest(){
        /****更新****/
        String key = "zsq";
        //1.单个新增或更新
        redisTemplate.opsForZSet().add(key,"0",0);
        //2.批量更新
        Set<ZSetOperations.TypedTuple<String>> batchVaule = new HashSet<>();
        Random rand = new Random();
        for (int i = 0;i < 10;i++) {
            ZSetOperations.TypedTuple<String> value = new DefaultTypedTuple(i + 1 + "",rand.nextInt(10) + 0.1);
            batchVaule.add(value);
        }
        redisTemplate.opsForZSet().add(key,batchVaule);
        //3.使用加法操作分数
        redisTemplate.opsForZSet().incrementScore(key,"0",1);

        /***查询***/
        //1.通过排名区间获取value
        redisTemplate.opsForZSet().range(key,0,-1);
        //2.通过排名区间获取value和score
        redisTemplate.opsForZSet().rangeWithScores(key,0,-1);
        //3.通过分数区间获取value
        redisTemplate.opsForZSet().rangeByScore(key,5,10);
        //4.通过分数区间获取value和score
        redisTemplate.opsForZSet().rangeByScoreWithScores(key,7,10);
        //5.查看个人排名
        redisTemplate.opsForZSet().rank(key,"0");
        //6.查看个人分数
        redisTemplate.opsForZSet().score(key,"0");
        //7.统计分数区间的人数
        redisTemplate.opsForZSet().count(key,5,10);
        //8.统计元素的个数
        redisTemplate.opsForZSet().size(key);
        redisTemplate.opsForZSet().zCard(key);

        /****删除****/
        //1.根据key/value删除
        redisTemplate.opsForZSet().remove(key,"0");
        //2.根据排名区间删除[start,end]
        redisTemplate.opsForZSet().removeRange(key,1,2);
        //3.根据分数区间删除[start,end]
        redisTemplate.opsForZSet().removeRangeByScore(key,9,10);
        //4.全部删除
        redisTemplate.opsForZSet().removeRange(key,0,-1);
    }

    private void hotArticleRank(){

    }
}
