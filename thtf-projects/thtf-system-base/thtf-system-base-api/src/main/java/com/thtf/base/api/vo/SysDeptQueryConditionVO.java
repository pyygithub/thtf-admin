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
 * 时间：  2019-12-31 16:10:54
 * 版本：  v1.0
 * ---------------------------
 */
@Data
@ApiModel(value = "SysDeptQueryConditionVO",description = "部门查询条件")
public class SysDeptQueryConditionVO {

    @ApiModelProperty("名称")
    private String name;

}