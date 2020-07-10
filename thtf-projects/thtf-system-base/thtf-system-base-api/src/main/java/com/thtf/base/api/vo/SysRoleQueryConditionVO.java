package com.thtf.base.api.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * ---------------------------
 * 模糊查询条件VO
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020-01-09 14:55:26
 * 版本：  v1.0
 * ---------------------------
 */
@Data
@ApiModel(value = "SysRoleQueryConditionVO",description = "角色查询条件")
public class SysRoleQueryConditionVO {

    @ApiModelProperty("名称")
    private String name;

}