package com.zte.auth.servletlistener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@Component
@Slf4j
public class CustomHttpSessionListener implements HttpSessionListener {
    public void sessionCreated(HttpSessionEvent se) {
        log.info("用户访问监听");
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        log.info("用户退出监听");
    }
}
