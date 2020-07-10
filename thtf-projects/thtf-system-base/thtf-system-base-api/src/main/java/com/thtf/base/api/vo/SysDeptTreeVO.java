package com.thtf.base.api.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * ---------------------------
 * 部门 (Dept)         
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019-12-31 16:10:54
 * 版本：  v1.0
 * ---------------------------
 */
@Data
@ApiModel(value = "SysDeptTreeVO",description = "部门类")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SysDeptTreeVO {

	@ApiModelProperty("部门ID")
    private String id;
	@ApiModelProperty("部门名称")
    private String name;
    @ApiModelProperty("部门名称")
    private String label;
	@ApiModelProperty("上级部门ID")
    private String parentId;
	@ApiModelProperty("排序")
    private Integer sort;
	@ApiModelProperty("备注")
    private String remark;
	@ApiModelProperty("创建人ID")
    private String createId;
	@ApiModelProperty("创建人名称")
    private String createName;
	@ApiModelProperty("创建时间")
    private Date createTime;
	@ApiModelProperty("修改人ID")
    private String updateId;
	@ApiModelProperty("修改人名称")
    private String updateName;
	@ApiModelProperty("修改时间")
    private Date updateTime;

    @ApiModelProperty("子节点列表")
	private List<SysDeptTreeVO> children;

}