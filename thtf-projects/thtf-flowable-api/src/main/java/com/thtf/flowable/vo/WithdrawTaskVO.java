package com.thtf.flowable.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Map;

/**
 * ---------------------------
 * 任务撤回VO类
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020-07-17 13:46:08
 * 版本：  v1.0
 * ---------------------------
 */
@Data
@ApiModel(value = "WithdrawTaskVO",description = "任务撤回VO类")
public class WithdrawTaskVO implements Serializable {

    @ApiModelProperty("任务id")
    @NotBlank(message = "任务id不能为空！")
    private String taskId;

    @ApiModelProperty("任务实例id")
    @NotBlank(message = "任务实例id不能为空！")
    private String processInstanceId;

    @ApiModelProperty("撤回原因")
    @NotBlank(message = "撤回原因不能为空！")
    private String comment;

    @ApiModelProperty("操作人")
    private String userCode;
}
