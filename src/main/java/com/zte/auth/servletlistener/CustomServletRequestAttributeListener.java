package com.zte.auth.servletlistener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
@Component
@Slf4j
public class CustomServletRequestAttributeListener implements ServletRequestAttributeListener {

    public void attributeAdded(ServletRequestAttributeEvent srae) {
        log.info("新增请求属性监听");
    }

    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        log.info("移除请求属性监听");
    }

    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        log.info("替换请求属性监听");
    }
}
