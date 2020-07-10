package com.thtf.base.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * ---------------------------
 * 角色 (SysRole)         
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020-01-09 14:55:26
 * 版本：  v1.0
 * ---------------------------
 */
@Data
@ApiModel(value = "SysRoleSaveOrUpdateVO",description = "角色保存和修改VO类")
public class SysRoleSaveOrUpdateVO {

	@ApiModelProperty("角色名称")
	private String name;
	@ApiModelProperty("角色标识")
	private String code;
	@ApiModelProperty("排序")
	private Integer sort;
	@ApiModelProperty("备注")
	private String remark;
	@ApiModelProperty("权限ID集合")
	private List<String> menuIds;
}