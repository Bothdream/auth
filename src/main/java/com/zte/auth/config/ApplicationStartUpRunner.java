package com.zte.auth.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j
public class ApplicationStartUpRunner implements ApplicationRunner {
    /**
     * 通过IDEA服务启动配置传参：
     * Program arguments: --name=zsq --age=18
     * @param args 参数
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("#############ApplicationArguments#############");
        System.out.println(Arrays.asList(args.getSourceArgs()));
        log.info("#############ApplicationArguments#############");
    }
}
