package com.thtf.base.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * ---------------------------
 * 系统日志保存和修改VO类
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019/12/27 11:45
 * 版本：  v1.0
 * ---------------------------
 */
@Data
@ApiModel(value = "SysLogSaveOrUpdateVO", description = "系统日志保存和修改VO类")
public class SysLogSaveOrUpdateVO implements Serializable {

    private static final long serialVersionUID = -6645663082545957651L;

    @ApiModelProperty("操作IP")
    private String requestIp;

    @ApiModelProperty("IP归属地")
    private String ipLocation;

    @ApiModelProperty("数据类型 1 操作记录 2异常记录")
    private Integer type;

    @ApiModelProperty("操作人ID")
    private String userId;

    @ApiModelProperty("操作人名称")
    private String username;

    @ApiModelProperty("操作描述")
    private String description;

    @ApiModelProperty("访问方法")
    private String actionMethod;

    @ApiModelProperty("请求url")
    private String actionUrl;

    @ApiModelProperty("请求参数")
    private String params;

    @ApiModelProperty("浏览器")
    private String browser;

    @ApiModelProperty("操作系统")
    private String operatingSystem;

    @ApiModelProperty("类路径")
    private String classPath;

    @ApiModelProperty("请求方式")
    private String requestMethod;

    @ApiModelProperty("操作类型（1查询/获取，2添加，3修改，4删除）")
    private Integer operateType;

    @ApiModelProperty("开始时间")
    private Date startTime;

    @ApiModelProperty("完成时间")
    private Date finishTime;

    @ApiModelProperty("消耗时间")
    private Long consumingTime;

    @ApiModelProperty("异常详情信息 堆栈信息")
    private String exDetail;

    @ApiModelProperty("异常描述 e.getMessage")
    private String exDesc;
}
