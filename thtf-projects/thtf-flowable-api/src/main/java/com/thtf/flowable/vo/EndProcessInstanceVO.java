package com.thtf.flowable.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Map;

/**
 * ---------------------------
 * 终止流程实例VO类
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020-07-17 09:13:30
 * 版本：  v1.0
 * ---------------------------
 */
@Data
@ApiModel(value = "EndProcessInstanceVO",description = "终止流程实例VO类")
public class EndProcessInstanceVO implements Serializable {

    @ApiModelProperty("流程实例的id")
    @NotBlank(message = "流程实例的id不能为空！")
    private String processInstanceId;

    @ApiModelProperty("任务id")
    @NotBlank(message = "任务id不能为空！")
    private String taskId;

    @ApiModelProperty("操作人code")
    @NotBlank(message = "操作人code 不能为空！")
    private String userCode;

    @ApiModelProperty("审批意见")
    @NotBlank(message = "审批意见不能为空！")
    private String message;

    @ApiModelProperty("审批类型 必填")
    @NotBlank(message = "审批类型不能为空！")
    private String type;

}
