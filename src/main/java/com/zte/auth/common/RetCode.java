package com.zte.auth.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Schema
public class RetCode {
    public final static String SUCESS_MSG_ID = "SUCESS";
    public final static String SUCESS_MSG_CODE = "000";
    public final static String SERVICE_ERROR_MSG_ID = "SERVICE_ERROR";
    public final static String SERVICE_ERROR_MSG_CODE = "001";
    public final static String BUSINESS_ERROR_MSG_ID = "BUSINESS_ERROR";
    public final static String BUSINESS_ERROR_MSG_CODE = "005";
    public final static String PERMISSION_ERROR_MSG_ID = "PERMISSION_ERROR";
    public final static String PERMISSION_ERROR_MSG_CODE = "003";
    public final static String VERIFICATION_ERROR_MSG_ID = "VERIFICATION_ERROR";
    public final static String VERIFICATION_ERROR_MSG_CODE = "002";

    @Schema(title = "响应ID")
    private String msgId;

    @Schema(title = "响应码")
    private String code;

    @Schema(title = "响应提示信息")
    private String msg;

    public RetCode() {
        this.msgId = SUCESS_MSG_ID;
        this.code = SUCESS_MSG_CODE;
    }
}
