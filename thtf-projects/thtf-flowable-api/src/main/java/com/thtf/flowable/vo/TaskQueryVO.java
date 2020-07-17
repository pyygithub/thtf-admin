package com.thtf.flowable.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Map;

/**
 * ---------------------------
 * 任务查询VO类
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020-07-17 13:46:08
 * 版本：  v1.0
 * ---------------------------
 */
@Data
@ApiModel(value = "TaskQueryVO",description = "任务查询VO类")
public class TaskQueryVO implements Serializable {

    @ApiModelProperty("员工编码")
    private String userCode;

    @ApiModelProperty("开始时间")
    private String startTime;

    @ApiModelProperty("结束时间")
    private String endTime;

}
