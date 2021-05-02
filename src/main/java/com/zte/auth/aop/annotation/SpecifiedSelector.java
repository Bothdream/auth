package com.zte.auth.aop.annotation;

import com.zte.auth.aop.SpecifiedValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 校验固定值的注解
 *
 *  @Data
 *  public class MyValidDTO {
 *     @QsmSpecifiedSelector(strValues = {"qsm", "hn"}, message = "姓名必须为指定值qsm,hn")
 *     private String name;
 *     @QsmSpecifiedSelector(intValues = {0, 1}, message = "学生标志必须为指定值0,1")
 *     private Integer studentFlag;
 *     @QsmSpecifiedSelector(enumValue = SspClaimEnum.Nation.class, message = "国家必须为指定值a,b")
 *     private String nation;
 * }
 *
 */

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {SpecifiedValidator.class})
@Repeatable(SpecifiedSelector.List.class)
public @interface SpecifiedSelector {
    //默认错误消息
    String message() default "必须为指定值";

    String[] strValues() default {};

    int[] intValues() default {};

    //使用指定枚举，1、使用属性命名code。2、枚举上使用QsmSpecifiedEnumValue
    Class<?> enumValue() default Class.class;

    //分组
    Class<?>[] groups() default {};

    //负载
    Class<? extends Payload>[] payload() default {};

    //指定多个时使用
    @Target({FIELD, METHOD, PARAMETER, ANNOTATION_TYPE})
    @Retention(RUNTIME)
    @Documented
    @interface List {
        SpecifiedSelector[] value();
    }
}
