package com.zte.auth.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CommandLineStartUpRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.info("#############2#############$");
        log.info(args.toString());
    }
}
