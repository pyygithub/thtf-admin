package com.thtf.base.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

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
@ApiModel(value = "SysUserVO",description = "用户类")
public class SysUserVO {

	@ApiModelProperty("用户ID")
    private String id;
	@ApiModelProperty("名称")
    private String name;
	@ApiModelProperty("用户名")
    private String username;
	@ApiModelProperty("密码")
    private String password;
	@ApiModelProperty("部门")
    private SysDeptVO dept;
	@ApiModelProperty("岗位")
    private SysJobVO job;
	@ApiModelProperty("邮箱")
    private String email;
	@ApiModelProperty("手机号")
    private String phone;
	@ApiModelProperty("头像")
    private String avatar;
	@ApiModelProperty("状态：0=正常 1=锁定")
    private String status;
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
	@ApiModelProperty("角色")
    private List<SysRoleVO> roleList;
}