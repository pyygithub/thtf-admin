package com.thtf.base.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ---------------------------
 * 角色菜单 (SysRoleMenu)         
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020-01-10 11:02:04
 * 版本：  v1.0
 * ---------------------------
 */
@Data
@ApiModel(value = "SysRoleMenuVO",description = "角色菜单类")
public class SysRoleMenuVO {

	@ApiModelProperty("ID")
    private String id;
	@ApiModelProperty("角色ID")
    private String roleId;
	@ApiModelProperty("菜单ID")
    private String menuId;

}