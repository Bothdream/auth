package com.zte.auth.common.enums;

import lombok.Getter;

/**
 * 1、校验异常 002
 * 2、权限异常 003
 * 3、业务异常 005
 * 4、其他异常 999
 * 5、正常      000
 */
@Getter
public enum RetCodeEnum {

    SUCCESS("000","操作成功"),
    AUTHORITY("003","权限异常"),
    VALIDATION("002","校验异常"),
    BUSINESS("005","业务异常"),
    OTHER("999","其他异常");

    private String code;

    private String msg;

    RetCodeEnum(String code,String msg){
        this.code = code;
        this.msg = msg;
    }
}
