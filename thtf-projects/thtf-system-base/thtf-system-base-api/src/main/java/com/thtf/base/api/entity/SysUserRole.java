package com.thtf.base.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName(value = "sys_user_role")
public class SysUserRole{

	/** ID */
	@TableId(type = IdType.ID_WORKER_STR)
	private String id;
	/** 用户ID */
	private String userId;
	/** 角色ID */
	private String roleId;

}