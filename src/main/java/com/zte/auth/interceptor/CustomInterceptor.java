package com.zte.auth.interceptor;

import com.zte.auth.entity.Policy;
import com.zte.auth.service.impl.PolicyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Set;

/**
 * 自定义的拦截器
 */
@Component
public class CustomInterceptor implements HandlerInterceptor {
    @Autowired
    private PolicyServiceImpl policyService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       String url = request.getRequestURI();
       String method = request.getMethod();
       System.out.println(url);
       System.out.println(method);
       HttpSession httpSession = request.getSession();
       System.out.println(httpSession.toString());
       Policy p = policyService.getPolicyById("sfdhjhfw3rwe809");
       if (null == p) {
            throw new Exception("無權限");
       } else {
            Set<String> powers = p.getPower();

            System.out.println("有權限");
       }
        System.out.println("preHandle");
       return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.println("afterCompletion");
    }

}
