package com.zte.auth.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class De {
    @Excel(name = "用户ID")
    @ApiModelProperty(value = "用戶Id")
    private Long userId;

    @Excel(name = "用户名称")
    @ApiModelProperty(value = "用户名称")
    private String userName;

    @Excel(name = "是否有效")
    @ApiModelProperty(value = "是否有效")
    private String enabledFlag;

    @Excel(name = "创建时间")
    @ApiModelProperty(value = "创建时间")
    private String creationDate;

    @Excel(name = "更新时间")
    @ApiModelProperty(value = "'更新时间'")
    private String lastUpdatedDate;
}
