package com.thtf.flowable.vo;

import com.thtf.flowable.enums.CommentTypeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * ---------------------------
 * 审批意见VO类
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020-07-17 09:28:22
 * 版本：  v1.0
 * ---------------------------
 */
@Data
@ApiModel(value = "ContentVO",description = "审批意见VO类")
public class CommentVO implements Serializable {

    @ApiModelProperty("流程实例的id")
    private String processInstanceId;

    @ApiModelProperty("任务id")
    protected String taskId;

    @ApiModelProperty("添加人")
    protected String userId;

    @ApiModelProperty("用户名")
    protected String userName;

    @ApiModelProperty("用户的头像链接")
    protected String userUrl;

    @ApiModelProperty("意见信息")
    protected String message;

    @ApiModelProperty("时间")
    protected Date time;
    /**
     *  @see  CommentTypeEnum
     */
    @ApiModelProperty("审批意见的类型")
    private String type;

    @ApiModelProperty("类型名称")
    private String typeName;

    @ApiModelProperty("任务名称")
    private String taskName;

    @ApiModelProperty("评论全信息")
    private String fullMsg;

    public CommentVO(){

    }

    public CommentVO(String userId, String processInstanceId, String type, String message) {
        this.userId = userId;
        this.processInstanceId = processInstanceId;
        this.message = message;
        this.type = type;
    }
    public CommentVO(String taskId, String userId, String processInstanceId, String type, String message) {
        this.taskId = taskId;
        this.userId = userId;
        this.processInstanceId = processInstanceId;
        this.message = message;
        this.type = type;
    }
}
