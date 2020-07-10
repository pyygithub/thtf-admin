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
@ApiModel(value = "SysUserRoleVO",description = "用户角色类")
public class SysUserRoleVO {

	@ApiModelProperty("ID")
    private String id;
	@ApiModelProperty("用户ID")
    private String userId;
	@ApiModelProperty("角色ID")
    private String roleId;

}