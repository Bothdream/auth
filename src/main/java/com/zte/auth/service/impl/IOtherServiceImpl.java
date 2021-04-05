package com.zte.auth.service.impl;

import com.zte.auth.service.IOtherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class IOtherServiceImpl implements IOtherService {
    private final Logger logger = LoggerFactory.getLogger(IOtherServiceImpl.class);
    @Override
    public String  doSomeThing(){
        logger.info("调用doSomeThing方法");
        return "do some thing";
    }
}
