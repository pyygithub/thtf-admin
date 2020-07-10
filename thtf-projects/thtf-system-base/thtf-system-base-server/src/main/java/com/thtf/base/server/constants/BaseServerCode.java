package com.thtf.base.server.constants;

import com.thtf.common.core.response.ResponseCode;

/**
 * ---------------------------
 * 通用用管理系统业务响应码
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019/12/27 16:02
 * 版本：  v1.0
 * ---------------------------
 */
public enum BaseServerCode implements ResponseCode {
    SAVE_ERROR(20001,"保存失败！"),
    UPDATE_ERROR(20002,"修改失败！"),
    DELETE_ERROR(20003,"删除失败！"),
    SELECT_ERROR(20004,"查询失败！"),
    RESULT_DATA_NONE(20005,"数据不存在！"),
    PARENT_IS_SELF(20006,"父级不能为自己！"),
    DEL_IDS_ISEMPTY(20007,"至少选择一条数据删除！");

    //操作代码
    private int code;

    //提示信息
    private String message;

    BaseServerCode(int code, String message){
        this.code = code;
        this.message = message;
    }

    @Override
    public int code() {
        return this.code;
    }

    @Override
    public String message() {
        return this.message;
    }
}
