package com.thtf.base.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.thtf.common.data.mybatis.model.CommonModel;
import lombok.Data;

/**
 * ---------------------------
 * 用户 (SysUser)         
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020-01-10 16:12:59
 * 版本：  v1.0
 * ---------------------------
 */
@Data
@TableName(value = "sys_user")
public class SysUser extends CommonModel{

	/** 用户ID */
	@TableId(type = IdType.ID_WORKER_STR)
	private String id;
	/** 名称 */
	private String name;
	/** 用户名 */
	private String username;
	/** 密码 */
	private String password;
	/** 部门ID */
	private String deptId;
	/** 岗位ID */
	private String jobId;
	/** 邮箱 */
	private String email;
	/** 手机号 */
	private String phone;
	/** 头像 */
	private String avatar;
	/** 状态：0=正常 1=锁定 */
	private String status;
}