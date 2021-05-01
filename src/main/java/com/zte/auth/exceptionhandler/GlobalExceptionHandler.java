package com.zte.auth.exceptionhandler;

import com.zte.auth.common.RetCode;
import com.zte.auth.common.ServiceData;
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

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ServiceData<Object> handleRuntimeException(ValidationException e){
        RetCode retCode = new RetCode();
        retCode.setCode(RetCode.BUSINESS_ERROR_MSG_CODE);
        retCode.setMsg(e.getMessage());
        retCode.setMsgId(RetCode.BUSINESS_ERROR_MSG_ID);
        ServiceData<Object> res = new ServiceData<>();
        res.setCode(retCode);
        return res;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ServiceData<Object> handleIllegalArgumentException(IllegalArgumentException e){
        RetCode retCode = new RetCode();
        retCode.setCode(RetCode.VERIFICATION_ERROR_MSG_CODE);
        retCode.setMsg(e.getMessage());
        retCode.setMsgId(RetCode.VERIFICATION_ERROR_MSG_ID);
        ServiceData<Object> res = new ServiceData<>();
        res.setCode(retCode);
        return res;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ServiceData<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        RetCode retCode = new RetCode();
        retCode.setCode(RetCode.VERIFICATION_ERROR_MSG_CODE);
        List<ObjectError> datas = e.getBindingResult().getAllErrors();
        List<String> errMsg = new ArrayList<>();
        for (ObjectError item : datas) {
            errMsg.add(item.getDefaultMessage());
        }
        retCode.setMsg(StringUtils.join(errMsg.toArray(),';'));
        retCode.setMsgId(RetCode.VERIFICATION_ERROR_MSG_ID);
        ServiceData<Object> res = new ServiceData<>();
        res.setCode(retCode);
        return res;
    }

    /**
     * MethodArgumentNotValidException 和  BindException 同时要写
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public ServiceData<Object> handleBindException(BindException e) {
        RetCode retCode = new RetCode();
        retCode.setCode(RetCode.VERIFICATION_ERROR_MSG_CODE);
        List<ObjectError> datas = e.getBindingResult().getAllErrors();
        List<String> errMsg = new ArrayList<>();
        for (ObjectError item : datas) {
            errMsg.add(item.getDefaultMessage());
        }
        retCode.setMsg(StringUtils.join(errMsg.toArray(),';'));
        retCode.setMsgId(RetCode.VERIFICATION_ERROR_MSG_ID);
        ServiceData<Object> res = new ServiceData<>();
        res.setCode(retCode);
        return res;
    }
}
