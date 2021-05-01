package com.zte.auth.servletlistener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
@Component
@Slf4j
public class CustomServletRequestListener implements ServletRequestListener {
    public void requestDestroyed(ServletRequestEvent sre) {
        log.info("结束请求监听");
    }
    public void requestInitialized(ServletRequestEvent sre) {
        log.info("发起请求监听");
    }
}
