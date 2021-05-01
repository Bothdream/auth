package com.zte.auth.servletlistener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
@Component
@Slf4j
public class CustomServletContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        log.info("应用启动监听");
    }

    public void contextDestroyed(ServletContextEvent sce) {
        log.info("应用销毁监听");
    }
}
