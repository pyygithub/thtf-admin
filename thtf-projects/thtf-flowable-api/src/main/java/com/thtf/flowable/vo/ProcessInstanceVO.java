package com.thtf.flowable.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * ---------------------------
 * 流程实例VO类
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020-07-13 10:29:07
 * 版本：  v1.0
 * ---------------------------
 */
@Data
@ApiModel(value = "StartProcessInstanceVO",description = "流程实例VO类")
public class ProcessInstanceVO implements Serializable {

    @ApiModelProperty("流程实例id")
    private String processInstanceId;

    @ApiModelProperty("流程定义id")
    private String processDefinitionId;

    @ApiModelProperty("激活状态 1激活 0挂起")
    private int suspensionState;

    @ApiModelProperty("表单名称")
    private String formName;

    @ApiModelProperty("表单主键")
    private String businessKey;

    @ApiModelProperty("开始时间")
    private Date startTime;
    /**
     * 结束时间
     */
    @ApiModelProperty("结束时间")
    private Date endTime;
    /**
     * 审批人
     */
    @ApiModelProperty("审批人")
    private String approver;
    /**
     * 发起人
     */
    @ApiModelProperty("发起人")
    private String starter;
    /**
     * 发起人id
     */
    @ApiModelProperty("发起人id")
    private String starterId;

    @ApiModelProperty("系统标识")
    private String systemSn;

}
