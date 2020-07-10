package com.thtf.base.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
@ApiModel(value = "SysDeptSaveOrUpdateVO",description = "部门保存和修改VO类")
public class SysDeptSaveOrUpdateVO {

	@ApiModelProperty("部门名称")
	private String name;
	@ApiModelProperty("上级部门ID")
	private String parentId;
	@ApiModelProperty("排序")
	private Integer sort;
	@ApiModelProperty("备注")
	private String remark;
}