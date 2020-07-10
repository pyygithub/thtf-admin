package com.thtf.base.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * ---------------------------
 * 系统日志查询条件VO类
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019/12/27 11:45
 * 版本：  v1.0
 * ---------------------------
 */
@Data
@ApiModel(value = "SysLogQueryConditionVO", description = "系统日志保存和修改VO类")
public class SysLogQueryConditionVO implements Serializable {

    private static final long serialVersionUID = -6645663082545957651L;

    @ApiModelProperty("数据类型 1 操作记录 2异常记录")
    private Integer type;

    @ApiModelProperty("操作人名称")
    private String username;

    @ApiModelProperty("操作类型（1查询/获取，2添加，3修改，4删除）")
    private Integer operateType;

    @ApiModelProperty("开始时间")
    private String startTime;

    @ApiModelProperty("完成时间")
    private String finishTime;

}
