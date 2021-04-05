package com.zte.auth.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "用户信息实体")
@Data
public class User{
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEnabledFlag() {
        return enabledFlag;
    }

    public void setEnabledFlag(String enabledFlag) {
        this.enabledFlag = enabledFlag;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(String lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }
}
