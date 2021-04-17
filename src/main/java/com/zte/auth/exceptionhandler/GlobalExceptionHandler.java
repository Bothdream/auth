package com.zte.auth.exceptionhandler;

import com.zte.auth.common.RetCode;
import com.zte.auth.common.ServiceData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ServiceData<Object> handleRuntimeException(RuntimeException e){
        RetCode retCode = new RetCode();
        retCode.setCode(RetCode.BUSINESS_ERROR_MSG_CODE);
        retCode.setMsg(e.getMessage());
        retCode.setMsgId(RetCode.BUSINESS_ERROR_MSG_ID);
        ServiceData<Object> res = new ServiceData<>();
        res.setCode(retCode);
        return res;
    }
}
