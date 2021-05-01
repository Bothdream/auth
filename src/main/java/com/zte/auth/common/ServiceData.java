package com.zte.auth.common;

import com.google.common.collect.Maps;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Map;
@Schema
@Data
public class ServiceData<T> {
    @Schema(title = "业务数据")
    private T bo;

    private RetCode code;
    @Schema(title = "其他数据")
    private Map<Object,Object> other;

    public ServiceData() {
        this.code = new RetCode(RetCode.SUCESS_MSG_ID,RetCode.SUCESS_MSG_CODE,"操作成功");
        this.other = Maps.newHashMap();
    }
}
