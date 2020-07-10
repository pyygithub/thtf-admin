package com.thtf.common.data.mybatis.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.util.Date;

/**
 * ========================
 * 公共字段
 * Created with IntelliJ IDEA.
 * User：pyy
 * Date：2019/9/19 16:44
 * Version: v1.0
 * ========================
 */
@Data
public class CommonModel {
    /** 创建人ID */
    @TableField(fill = FieldFill.INSERT)
    private String createId;

    /** 创建人名称 */
    @TableField(fill = FieldFill.INSERT)
    private String createName;

    /** 创建时间 */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /** 修改人编码 */
    @TableField(fill = FieldFill.UPDATE)
    private String updateId;

    /** 修改人名称 */
    @TableField(fill = FieldFill.UPDATE)
    private String updateName;

    /** 修改时间 */
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    /** 删除标记 */
    @TableLogic
    private Integer deletedFlag = 0;
}
