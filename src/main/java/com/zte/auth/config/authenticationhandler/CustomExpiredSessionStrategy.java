package com.zte.auth.config.authenticationhandler;

import com.alibaba.fastjson.JSON;
import com.zte.auth.common.ServiceData;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * 自定义session超时处理机制
 */
@Component
public class CustomExpiredSessionStrategy implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        String mss = "您的登录已经超时或者已经在另外一台机器登录，您被迫下线。" + event.getSessionInformation().getLastRequest();
        String res = JSON.toJSONString(ServiceData.authorityException(mss));
        event.getResponse().setContentType("application/json; charset=UTF-8");
        event.getResponse().getWriter().write(res);
    }
}
