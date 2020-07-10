package com.thtf.base.api.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * ---------------------------
 * 部门 (Dept)         
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019-12-31 16:10:54
 * 版本：  v1.0
 * ---------------------------
 */
@Data
@ApiModel(value = "SysDeptVO",description = "部门类")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysDeptVO {
	@ApiModelProperty("部门ID")
    private String id;
	@ApiModelProperty("部门名称")
    private String name;
}