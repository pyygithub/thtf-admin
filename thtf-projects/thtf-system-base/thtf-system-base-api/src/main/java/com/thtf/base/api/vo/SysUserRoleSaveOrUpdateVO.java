package com.thtf.base.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ---------------------------
 * 用户角色 (SysUserRole)         
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020-01-10 16:06:25
 * 版本：  v1.0
 * ---------------------------
 */
@Data
@ApiModel(value = "SysUserRoleSaveOrUpdateVO",description = "用户角色保存和修改VO类")
public class SysUserRoleSaveOrUpdateVO {

	@ApiModelProperty("用户ID")
	private String userId;
	@ApiModelProperty("角色ID")
	private String roleId;

}