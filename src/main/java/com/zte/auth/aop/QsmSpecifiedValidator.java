package com.zte.auth.aop;

import com.zte.auth.aop.annotation.QsmSpecifiedSelector;
import lombok.SneakyThrows;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Method;

public class QsmSpecifiedValidator implements ConstraintValidator<QsmSpecifiedSelector, Object> {
    private String[] strValues;
    private int[] intValues;
    private Class<?> cls;

    @Override
    public void initialize(QsmSpecifiedSelector constraintAnnotation) {
        strValues = constraintAnnotation.strValues();
        intValues = constraintAnnotation.intValues();
        cls = constraintAnnotation.enumValue();
    }

    @SneakyThrows
    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        if (cls.isEnum()) {
            Object[] objects = cls.getEnumConstants();
            for (Object obj : objects) {
                //此处方法getCode需要根据自己项目枚举的命名而变化
                Method method = cls.getDeclaredMethod("getCode");
                String expectValue = String.valueOf(method.invoke(obj));
                if (expectValue.equals(String.valueOf(value))) {
                    return true;
                }
            }
        } else {
            if (value instanceof String) {
                for (String s : strValues) {
                    if (s.equals(value)) {
                        return true;
                    }
                }
            } else if (value instanceof Integer) {
                for (Integer s : intValues) {
                    if (s == value) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
