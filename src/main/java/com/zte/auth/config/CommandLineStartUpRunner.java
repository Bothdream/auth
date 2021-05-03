package com.zte.auth.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Slf4j
public class CommandLineStartUpRunner implements CommandLineRunner {
    /**
     *   通过IDEA服务启动配置传参：
     *   Program arguments: --name=zsq --age=18
     * @param args 命令行传入的参数
     * @throws Exception
     */
    @Override
    public void run(String... args) throws Exception {
        log.info("#############CommandLine#############$");
        System.out.println(Arrays.asList(args));
        log.info("#############CommandLine#############$");
    }
}
