package com.thtf.flowable.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * ---------------------------
 * 任务VO类
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020-07-17 13:46:08
 * 版本：  v1.0
 * ---------------------------
 */
@Data
@ApiModel(value = "TaskVO",description = "任务VO类")
public class TaskVO implements Serializable {

    @ApiModelProperty("任务id")
    private String taskId;

    @ApiModelProperty("任务名称")
    private String taskName;

    @ApiModelProperty("审批人")
    private String approver;

    @ApiModelProperty("审批人id")
    private String approverId;

    @ApiModelProperty("表单名称")
    private String formName;

    @ApiModelProperty("业务主键")
    private String businessKey;

    @ApiModelProperty("流程实例id")
    private String processInstanceId;

    @ApiModelProperty("开始时间")
    private Date startTime ;

    @ApiModelProperty("结束时间")
    private Date endTime;

    @ApiModelProperty("租户标示")
    private String tenantId;

}
