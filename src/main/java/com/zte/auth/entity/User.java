package com.zte.auth.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@JsonPropertyOrder({"userName","enabledFlag"})
@Schema
public class User{
    @Excel(name = "用户ID")
    @Schema(title = "用户ID")
    @JsonIgnore
    private Long id;

    @Excel(name = "用户名称")
    @Schema(title = "用户名称")
    @JsonProperty("account")
    @NotBlank(message = "用户名不能为空")
    private String name;

    @Excel(name = "是否有效")
    @Schema(title = "是否有效")
    private String enabledFlag;

    @Excel(name = "创建时间")
    @Schema(title = "创建时间")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NotBlank(message = "创建时间不能为空")
    private String creationDate;

    @Excel(name = "更新时间")
    @Schema(title = "更新时间")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String lastUpdatedDate;
}
