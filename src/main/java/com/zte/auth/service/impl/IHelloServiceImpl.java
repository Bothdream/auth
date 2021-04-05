package com.zte.auth.service.impl;

import com.zte.auth.service.IOtherService;
import com.zte.auth.service.IHelloService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IHelloServiceImpl implements IHelloService {
    private final Logger logger = LoggerFactory.getLogger(IHelloServiceImpl.class);

    @Autowired
    private IOtherService iOtherServiceImpl;

    @Override
    public String sayHello() {
        logger.info("调用Hello方法");
        String other = iOtherServiceImpl.doSomeThing();
        return "Hello World!" + this.b() + ":" + other;
    }
    @Override
    public Long b() {
        logger.info("调用b方法");
        return 1L;
    }
}
