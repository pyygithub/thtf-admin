package com.thtf.base.server.constants;

import io.swagger.annotations.ApiModelProperty;

/**
 * ---------------------------
 * 系统常量
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020/1/15 14:07
 * 版本：  v1.0
 * ---------------------------
 */
public interface SystemConstant {

    // 是否外链：0=否 1=是
    Integer EXTERNAL = 1;
    Integer NOT_EXTERNAL = 0;

    // 是否隐藏：0=否 1=是"
    Integer HIDDEN = 1;
    Integer NOT_HIDDEN = 0;

    //是否缓存：0=否 1=是"
    Integer CACHE = 1;
    Integer NOT_CACHE = 0;

    // 类型：1=目录 2=菜单 3=按钮
    Integer DIRECTORY = 1;
    Integer MENU = 2;
    Integer BUTTON = 3;
}
