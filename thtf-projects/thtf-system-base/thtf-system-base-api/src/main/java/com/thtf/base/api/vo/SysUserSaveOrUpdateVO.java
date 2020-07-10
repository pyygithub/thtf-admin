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
@ApiModel(value = "SysUserSaveOrUpdateVO",description = "用户保存和修改VO类")
public class SysUserSaveOrUpdateVO {
	@ApiModelProperty("名称")
	private String name;
	@ApiModelProperty("用户名")
	private String username;
	@ApiModelProperty("密码")
	private String password;
	@ApiModelProperty("部门ID")
	private String deptId;
	@ApiModelProperty("岗位ID")
	private String jobId;
	@ApiModelProperty("角色ID集合")
	private List<String> roleIds;
	@ApiModelProperty("邮箱")
	private String email;
	@ApiModelProperty("手机号")
	private String phone;
	@ApiModelProperty("头像")
	private String avatar;
	@ApiModelProperty("状态：0=正常 1=锁定")
	private String status;

}