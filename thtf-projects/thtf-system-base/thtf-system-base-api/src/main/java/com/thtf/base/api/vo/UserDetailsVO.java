package com.thtf.base.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * ---------------------------
 * 用户 (UserDetailsVO)
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020-01-10 16:12:59
 * 版本：  v1.0
 * ---------------------------
 */
@Data
@ApiModel(value = "UserDetailsVO",description = "用户认证信息")
public class UserDetailsVO {
    /**
     * 用户基本信息
     */
    private SysUserVO sysUser;

    /**
     * 用户权限
     */
    Set<String> permissions;
}