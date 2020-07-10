package com.thtf.base.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "SysMenuVO",description = "菜单类")
public class SysMenuVO {

	@ApiModelProperty("ID")
    private String id;
	@ApiModelProperty("名称")
    private String name;
	@ApiModelProperty("父级ID")
    private String parentId;
	@ApiModelProperty("是否外链：0=否 1=是")
    private Integer iframe;
	@ApiModelProperty("排序")
    private Integer sort;
	@ApiModelProperty("图标")
    private String icon;
	@ApiModelProperty("链接地址")
    private String path;
	@ApiModelProperty("是否缓存：0=否 1=是")
    private Integer cache;
	@ApiModelProperty("是否隐藏：0=否 1=是")
    private Integer hidden;
	@ApiModelProperty("组件名称")
    private String componentName;
	@ApiModelProperty("组件路径")
    private String componentPath;
	@ApiModelProperty("类型：1=目录 2=菜单 3=按钮")
    private Integer type;
	@ApiModelProperty("权限标识")
    private String permission;
	@ApiModelProperty("创建人ID")
    private String createId;
	@ApiModelProperty("创建人名称")
    private String createName;
	@ApiModelProperty("创建时间")
    private java.util.Date createTime;
	@ApiModelProperty("修改人ID")
    private String updateId;
	@ApiModelProperty("修改人名称")
    private String updateName;
	@ApiModelProperty("修改时间")
    private java.util.Date updateTime;
}