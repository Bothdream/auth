package com.zte.auth.exceptionhandler;

import com.zte.auth.common.ServiceData;
import com.zte.auth.common.constants.SpecialConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;

/**
 * 全局异常处理
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

     /* 校验处理
     * @param e 参数异常
     * @return 返回值
     */
    @ExceptionHandler(ValidationException.class)
    public ServiceData handleRuntimeException(ValidationException e){
        return ServiceData.validationException(e.getMessage());
    }

     /* 不合法参数异常处理
     * @param e 参数异常
     * @return 返回值
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ServiceData handleIllegalArgumentException(IllegalArgumentException e){
        return ServiceData.validationException(e.getMessage());
    }

    /**
     * 参数异常处理
     * @param e 参数异常
     * @return 返回值
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ServiceData handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return builtValidationServiceData(e.getBindingResult().getAllErrors());
    }

    /**
     * MethodArgumentNotValidException 和  BindException 同时要写
     * @param e 绑定异常
     * @return 返回值
     */
    @ExceptionHandler(BindException.class)
    public ServiceData handleBindException(BindException e) {
        return builtValidationServiceData(e.getBindingResult().getAllErrors());
    }

    /**
     * 组装校验异常信息
     * @param allErrors 异常信息
     * @return 返回值
     */
    private ServiceData builtValidationServiceData(List<ObjectError> allErrors){
        List<String> errMsg = new ArrayList<>();
        for (ObjectError item : allErrors) {
            errMsg.add(item.getDefaultMessage());
        }
        return ServiceData.validationException(StringUtils.join(errMsg.toArray(),SpecialConstants.SEMICOLON));
    }
}
