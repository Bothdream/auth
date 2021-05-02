package com.zte.auth.aop.aspect;

import com.zte.auth.aop.annotation.Verifiable;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 校验请求参数的切面
 */
@Aspect
@Component
@Slf4j
public class CheckRequestParameterAop {

    @Pointcut("@annotation(com.zte.auth.aop.annotation.CheckRequestParameter)")
    public void pointcut(){}

    @Before("pointcut()")
    public void pointcut(JoinPoint joinPoint){
       Object[] parameter = joinPoint.getArgs();
       for (Object  item : parameter) {
           if (item instanceof Verifiable) {
               Verifiable temp = (Verifiable)item;
               temp.verify();
           }
       }
    }
}
