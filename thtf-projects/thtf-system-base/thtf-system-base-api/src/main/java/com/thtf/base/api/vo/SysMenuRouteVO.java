package com.thtf.base.api.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * ---------------------------
 * 路由菜单类 (SysMenu)
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020-01-07 11:13:01
 * 版本：  v1.0
 * ---------------------------
 */
@Data
@ApiModel(value = "SysMenuRouteVO",description = "路由菜单类")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysMenuRouteVO {

    private String name;

    private String path;

    private Boolean hidden;

    private String redirect;

    private String component;

    private Boolean alwaysShow;

    private SysMenuMetaVO meta;

    private List<SysMenuRouteVO> children;
}