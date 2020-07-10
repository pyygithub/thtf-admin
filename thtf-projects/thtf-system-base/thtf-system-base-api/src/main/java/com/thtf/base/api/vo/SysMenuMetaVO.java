package com.thtf.base.api.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

/**
 * ---------------------------
 * 路由Meta类 (SysMenuMetaVO)
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020-01-07 11:13:01
 * 版本：  v1.0
 * ---------------------------
 */
@Data
@ApiModel(value = "SysMenuMetaVO",description = "路由Meta类")
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class SysMenuMetaVO {
    private String title;

    private String icon;

    private Boolean noCache;
}