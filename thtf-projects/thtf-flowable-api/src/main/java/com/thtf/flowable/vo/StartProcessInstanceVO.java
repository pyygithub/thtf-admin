package com.thtf.flowable.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
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
public class StartProcessInstanceVO implements Serializable {

    @ApiModelProperty("流程定义ID")
    @NotBlank(message = "流程定义ID不能为空！")
    private String processDefinitionId;

    @ApiModelProperty("业务系统id")
    @NotBlank(message = "业务系统id不能为空！")
    private String businessKey;
    /**
     * 启动流程变量 选填
     */
    @ApiModelProperty("启动流程变量")
    private Map<String, Object> variables;

    @ApiModelProperty("申请人编码")
    @NotBlank(message = "申请人编码不能为空！")
    private String currentUserCode;

    @ApiModelProperty("租户ID")
    private String tendantId;
    /**
     * 表单显示名称 必填
     */
    @ApiModelProperty("表单显示名称")
    @NotBlank(message = "表单显示名称不能为空！")
    private String formName;

}
