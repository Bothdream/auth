package com.zte.auth.config;

import com.zte.auth.config.authenticationhandler.CustomExpiredSessionStrategy;
import com.zte.auth.config.authenticationhandler.CustomUserDetailService;
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
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private LoginSuccessHandler loginSuccessHandler;

    @Resource
    private LoginFailHandler loginFailHandler;

    @Resource
    private CustomExpiredSessionStrategy customExpiredSessionStrategy;

    @Resource
    private CustomUserDetailService customUserDetailService;

    @Resource(name = "primaryDataSource")
    private DataSource dataSource;

    @Resource
    private PersistentTokenRepository persistentTokenRepository;

    @Override
    public void configure(HttpSecurity http) throws Exception{
        http
                .rememberMe() //记住密码功能配置
                    .rememberMeParameter("remember-me")
                    .rememberMeCookieName("cccxx")
                    .tokenValiditySeconds(2*24*60*60)
                    .tokenRepository(persistentTokenRepository)
                .and() // csrf 配置
                    .csrf()
                    .disable()
                .formLogin() // 表单登录配置
                    .loginPage("/login.html")// 一旦用户的请求没有权限就跳转到该登录页面
                    .loginProcessingUrl("/login") // 登录表单中action的地址，即处理认证请求的路径
                    .passwordParameter("password") // form表单密码输入框的参数名
                    .usernameParameter("username") // form表单用户名输入框的参数名
                    .successHandler(loginSuccessHandler) // 自定义登录成功的处理逻辑
                    .failureHandler(loginFailHandler)//自定义登录失败的处理逻辑
                .and() // 权限过滤配置
                    .authorizeRequests()
                    .antMatchers("/login.html","/login")
                    .permitAll()
                    .anyRequest()
                    .access("@customRbacService.hasPermission(request,authentication)")// 动态加载资源鉴权规则
                .and() // session 管理配置
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                    .sessionFixation().migrateSession()
                    .maximumSessions(1)
                    .maxSessionsPreventsLogin(false)
                    .expiredSessionStrategy(customExpiredSessionStrategy); //sesscion超时的处理策略
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
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

    // 将记住密码的token持久化到数据库
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        //要创建登录信息表：
        // create table persistent_logins (username varchar(64) not null, series varchar(64) primary key, token varchar(64) not null, last_used timestamp not null)
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }
}
