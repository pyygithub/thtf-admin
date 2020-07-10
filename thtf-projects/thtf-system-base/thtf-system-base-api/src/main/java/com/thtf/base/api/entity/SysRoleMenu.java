package com.thtf.base.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName(value = "sys_role_menu")
public class SysRoleMenu {

	/** ID */
	@TableId(type = IdType.ID_WORKER_STR)
	private String id;
	/** 角色ID */
	private String roleId;
	/** 菜单ID */
	private String menuId;

}