package com.zte.auth.common;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
@Setter
@Getter
public class ServiceData<T> {
    private T bo;
    private RetCode code;
    private Map<Object,Object> other;

    public ServiceData() {
        this.code = new RetCode(RetCode.SUCESS_MSG_ID,RetCode.SUCESS_MSG_CODE,"操作成功");
        this.other = new HashMap<>(8);
    }
}
