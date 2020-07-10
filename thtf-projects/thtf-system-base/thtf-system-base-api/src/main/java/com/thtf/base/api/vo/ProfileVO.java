package com.thtf.base.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * ---------------------------
 * 用户配置信息类
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019-11-14 10:35:07
 * 版本：  v1.0
 * ---------------------------
 */
@Data
@ApiModel(value = "ProfileVO",description = "用户配置信息类")
public class ProfileVO {

    @ApiModelProperty("用户ID")
    private String id;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("用户名")
    private String username;
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

    @ApiModelProperty("角色包含权限信息")
    private Map<String, Object> roles = new HashMap<>();
}
