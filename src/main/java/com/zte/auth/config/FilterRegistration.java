package com.zte.auth.config;

import com.zte.auth.filter.CustFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 过滤器配置类
 */
@Configuration
public class FilterRegistration {

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        // Filter可以new，也可以使用依赖注入的Bean
        registration.setFilter(new CustFilter());
        //拦截路径
        registration.addUrlPatterns("/*");
        //过滤器名称
        registration.setName("custFilter");
        //设置顺序
        registration.setOrder(10);
        return registration;
    }
}
