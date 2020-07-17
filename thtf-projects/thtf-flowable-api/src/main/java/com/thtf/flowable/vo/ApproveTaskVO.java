package com.thtf.flowable.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * ---------------------------
 * 审批（同意）VO类
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020-07-17 13:46:08
 * 版本：  v1.0
 * ---------------------------
 */
@Data
@ApiModel(value = "ApproveTaskVO",description = "审批（同意）VO类")
public class ApproveTaskVO implements Serializable {

    @ApiModelProperty("任务id")
    @NotBlank(message = "任务id不能为空！")
    private String taskId;

    @ApiModelProperty("任务实例id")
    @NotBlank(message = "任务实例id不能为空！")
    private String processInstanceId;

    @ApiModelProperty("审批意见")
    @NotBlank(message = "审批意见不能为空！")
    private String comment;

    @ApiModelProperty("任务参数")
    private Map<String, Object> variables;

    @ApiModelProperty("操作人")
    private String userCode;
}
