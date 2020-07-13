package com.thtf.flowable.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ---------------------------
 * 用户VO类
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020-07-13 10:29:07
 * 版本：  v1.0
 * ---------------------------
 */
@Data
@ApiModel(value = "UserVO",description = "用户信息")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserVO {
	@ApiModelProperty("用户ID")
    private String id;

	@ApiModelProperty("名称")
    private String name;
}