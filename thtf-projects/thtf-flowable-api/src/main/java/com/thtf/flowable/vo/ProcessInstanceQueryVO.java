package com.thtf.flowable.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * ---------------------------
 * 流程实例QueryVO类
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020-07-13 10:29:07
 * 版本：  v1.0
 * ---------------------------
 */
@Data
@ApiModel(value = "ProcessInstanceQueryVO",description = "流程实例QueryVO类")
public class ProcessInstanceQueryVO implements Serializable {

    @ApiModelProperty("流程名称")
    private String formName;

    @ApiModelProperty("发起人编码")
    private String userCode;

    @ApiModelProperty("发起人名称")
    private String userName;

}
