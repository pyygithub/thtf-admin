package com.thtf.base.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.thtf.common.data.mybatis.model.CommonModel;
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
@TableName(value = "sys_job")
public class SysJob extends CommonModel {

	/** 岗位ID */
	@TableId(type = IdType.ID_WORKER_STR)
	private String id;
	/** 部门名称 */
	private String name;
	/** 部门ID */
	private String deptId;
	/** 排序 */
	private Integer sort;
	/** 备注 */
	private String remark;
}