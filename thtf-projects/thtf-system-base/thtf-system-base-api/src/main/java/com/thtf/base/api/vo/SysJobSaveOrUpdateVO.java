package com.thtf.base.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ---------------------------
 * 岗位 (SysJob)         
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020-01-03 16:01:11
 * 版本：  v1.0
 * ---------------------------
 */
@Data
@ApiModel(value = "SysJobSaveOrUpdateVO",description = "岗位保存和修改VO类")
public class SysJobSaveOrUpdateVO {

	@ApiModelProperty("部门名称")
	private String name;
	@ApiModelProperty("部门ID")
	private String deptId;
	@ApiModelProperty("排序")
	private Integer sort;
	@ApiModelProperty("备注")
	private String remark;
}