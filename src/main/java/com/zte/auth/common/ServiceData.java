package com.zte.auth.common;

import com.zte.auth.common.enums.RetCodeEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 返回前端的业务对象
 */
@Schema
@Data
public class ServiceData<T> {

    @Schema(title = "业务数据")
    private T bo;

    @Schema(title = "状态码")
    private String code;

    @Schema(title = "提示信息")
    private String msg;

    public ServiceData() { }

    /**
     * 操作成功
     * @param bo 业务对象
     * @return 返回值
     */
    public static ServiceData success(Object bo){
        ServiceData<Object> res = new ServiceData<>();
        res.setBo(bo);
        res.setCode(RetCodeEnum.SUCCESS.getCode());
        res.setMsg(RetCodeEnum.SUCCESS.getMsg());
        return res;
    }

    /**
     * 授权异常
     * @param msg 提示信息
     * @return 返回值
     */
    public static ServiceData authorityException(String msg){
        ServiceData<Object> res = new ServiceData<>();
        res.setCode(RetCodeEnum.AUTHORITY.getCode());
        res.setMsg(msg);
        return res;
    }

    /**
     * 校验异常
     * @param msg 提示信息
     * @return 返回值
     */
    public static ServiceData validationException(String msg){
        ServiceData<Object> res = new ServiceData<>();
        res.setCode(RetCodeEnum.VALIDATION.getCode());
        res.setMsg(msg);
        return res;
    }

    /**
     * 业务异常
     * @param msg 提示信息
     * @return 返回值
     */
    public static ServiceData businessException(String msg){
        ServiceData<Object> res = new ServiceData<>();
        res.setCode(RetCodeEnum.BUSINESS.getCode());
        res.setMsg(msg);
        return res;
    }

    /**
     *  其他异常
     * @param msg 提示信息
     * @return 返回值
     */
    public static ServiceData otherException(String msg){
        ServiceData<Object> res = new ServiceData<>();
        res.setCode(RetCodeEnum.OTHER.getCode());
        res.setMsg(msg);
        return res;
    }
}
