package com.zte.auth.config;

import com.zte.auth.config.authenticationhandler.CustomExpiredSessionStrategy;
import com.zte.auth.config.authenticationhandler.LoginFailHandler;
import com.zte.auth.config.authenticationhandler.LoginSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private LoginSuccessHandler loginSuccessHandler;

    @Resource
    private LoginFailHandler loginFailHandler;

    @Resource
    private CustomExpiredSessionStrategy customExpiredSessionStrategy;

    @Override
    public void configure(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .formLogin()
                .loginPage("/login.html")// 一旦用户的请求没有权限就跳转到该登录页面
                .loginProcessingUrl("/login") // 登录表单中action的地址，即处理认证请求的路径
                .passwordParameter("password") // form表单密码输入框的参数名
                .usernameParameter("username") // form表单用户名输入框的参数名
//                .defaultSuccessUrl("/")// 登录成功后默认跳转的路径
                .successHandler(loginSuccessHandler) // 自定义登录成功的处理逻辑
                .failureHandler(loginFailHandler)//自定义登录失败的处理逻辑
                .and()
                .authorizeRequests()
                    .antMatchers("/login.html","/login").permitAll()
                    .antMatchers("/","/biz1","/biz2")
                            .hasAnyAuthority("ROLE_user","ROLE_admin")
                    .antMatchers("/syslog")
                            .hasAnyAuthority("sys:log")
                    .antMatchers("/sysuser")
                            .hasAnyAuthority("sys:user")
                .anyRequest().authenticated()
                .and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .sessionFixation().migrateSession()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(false)
                .expiredSessionStrategy(customExpiredSessionStrategy); //sesscion超时的处理策略
    }


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password(passwordEncoder().encode("123456"))
                .authorities("ROLE_user")
          .and().withUser("admin")
                .password(passwordEncoder().encode("123456"))
                .authorities("sys:log","sys:user","ROLE_admin")
          .and().passwordEncoder(passwordEncoder()); //配置加密的算法
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    /**
     * 静态资源的配置访问：
     * 该操作不会经过过滤器
     * @param webSecurity
     */
    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity.ignoring().antMatchers("/css/**","/fonts/**","/img/**","/js/**");
    }
}
