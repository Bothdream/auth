package com.zte.auth.config.authenticationhandler;

import com.alibaba.fastjson.JSON;
import com.zte.auth.common.ServiceData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义登录成功的处理逻辑
 */
@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Value("${spring.security.logintype}")
    private String loginType;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        if (loginType.equalsIgnoreCase("JSON")) {
            httpServletResponse.setContentType("application/json; charset=UTF-8");
            //返回json的格式给前端
            httpServletResponse.getWriter().write(JSON.toJSONString(ServiceData.success(null)));
        } else {
            //调用父类的处理方法
            httpServletResponse.setContentType("text/html; charset=UTF-8");
            super.onAuthenticationSuccess(httpServletRequest,httpServletResponse,authentication);
        }
    }
}
