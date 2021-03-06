package com.zte.auth.config;

import com.zte.auth.interceptor.CustomInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 注册拦截器
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private CustomInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //注册多个拦截器 拦截规则
        // 多个拦截器时，依次添加，执行顺序按添加顺序
        registry.addInterceptor(interceptor).addPathPatterns("/user/**");
    }
}
