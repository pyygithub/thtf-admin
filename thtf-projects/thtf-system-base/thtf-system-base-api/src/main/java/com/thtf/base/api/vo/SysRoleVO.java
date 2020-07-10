package com.thtf.base.api.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

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
@ApiModel(value = "SysRoleVO",description = "角色类")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysRoleVO {

	@ApiModelProperty("岗位ID")
    private String id;
	@ApiModelProperty("角色名称")
    private String name;
	@ApiModelProperty("角色标识")
    private String code;
	@ApiModelProperty("排序")
    private Integer sort;
	@ApiModelProperty("备注")
    private String remark;
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
    @ApiModelProperty("角色关联菜单ID集合")
	private List<String> menuIds;
}