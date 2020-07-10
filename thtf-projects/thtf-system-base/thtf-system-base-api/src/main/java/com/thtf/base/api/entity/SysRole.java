package com.thtf.base.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.thtf.common.data.mybatis.model.CommonModel;
import lombok.Data;

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
@TableName(value = "sys_role")
public class SysRole extends CommonModel {

	/** 岗位ID */
	@TableId(type = IdType.ID_WORKER_STR)
	private String id;
	/** 角色名称 */
	private String name;
	/** 角色标识 */
	private String code;
	/** 排序 */
	private Integer sort;
	/** 备注 */
	private String remark;
}