package com.thtf.base.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.thtf.common.data.mybatis.model.CommonModel;
import lombok.Data;

/**
 * ---------------------------
 * 菜单 (SysMenu)         
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020-01-07 11:13:01
 * 版本：  v1.0
 * ---------------------------
 */
@Data
@TableName(value = "sys_menu")
public class SysMenu extends CommonModel {

	/** ID */
	@TableId(type = IdType.ID_WORKER_STR)
	private String id;
	/** 名称 */
	private String name;
	/** 父级ID */
	private String parentId;
	/** 是否外链：0=否 1=是 */
	private Integer iframe;
	/** 排序 */
	private Integer sort;
	/** 图标 */
	private String icon;
	/** 链接地址 */
	private String path;
	/** 是否缓存：0=否 1=是 */
	private Integer cache;
	/** 是否隐藏：0=否 1=是 */
	private Integer hidden;
	/** 组件名称 */
	private String componentName;
	/** 组件路径 */
	private String componentPath;
	/** 类型：1=目录 2=菜单 3=按钮 */
	private Integer type;
	/** 权限标识 */
	private String permission;
}