package com.thtf.base.api.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * ---------------------------
 * 岗位 (SysJob)         
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020-01-03 16:01:11
 * 版本：  v1.0
 * ---------------------------
 */
@Data
@ApiModel(value = "SysJobVO",description = "岗位类")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysJobVO {

	@ApiModelProperty("岗位ID")
    private String id;
	@ApiModelProperty("部门名称")
    private String name;
	@ApiModelProperty("部门")
    private SysDeptVO dept;
	@ApiModelProperty("排序")
    private Integer sort;
	@ApiModelProperty("备注")
    private String remark;
	@ApiModelProperty("创建人ID")
    private String createId;
	@ApiModelProperty("创建人名称")
    private String createName;
	@ApiModelProperty("创建时间")
    private java.util.Date createTime;
	@ApiModelProperty("修改人ID")
    private String updateId;
	@ApiModelProperty("修改人名称")
    private String updateName;
	@ApiModelProperty("修改时间")
    private java.util.Date updateTime;
}