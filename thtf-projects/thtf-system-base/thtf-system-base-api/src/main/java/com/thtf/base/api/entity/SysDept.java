package com.thtf.base.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.thtf.common.data.mybatis.model.CommonModel;
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
@TableName(value = "sys_dept")
public class SysDept extends CommonModel {

	/** 部门ID */
	@TableId(type = IdType.ID_WORKER_STR)
	private String id;
	/** 部门名称 */
	private String name;
	/** 上级部门ID */
	private String parentId;
	/** 排序 */
	private Integer sort;
	/** 备注 */
	private String remark;
}