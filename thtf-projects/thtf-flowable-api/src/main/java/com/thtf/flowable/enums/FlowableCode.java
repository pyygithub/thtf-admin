package com.thtf.flowable.enums;

import com.thtf.common.core.response.ResponseCode;

/**
 * ---------------------------
 * Flowable流程响应状态码
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019/12/26 16:25
 * 版本：  v1.0
 * ---------------------------
 */
public enum FlowableCode implements ResponseCode {
    FLOW_FILE_TYPE_INVALID(50001,"Invalid file name, only .bpmn20.xml files are supported"),
    FLOW_FILE_NOT_FOUND(50002,"No process found in definition！"),
    FLOW_FILE_UPLOAD_FAIL(50003,"Upload file failed！"),
    FLOW_DEPLOY_FAIL(50004,"Flow deploy failed！"),
    FLOW_GENERATE_BPMN_FAIL(50005,"Could not generate BPMN 2.0 model！"),
    FLOW_NO_FOUND_MODEL(50006,"Could not found model！"),
    FLOW_DELETE_FAIL(50007,"Flow model delete fail !"),
    FLOW_SUSPENDED(50008,"此流程已经挂起,请联系系统管理员!");


    /**
     * 操作代码
     */
    private int code;

    /**
     * 提示信息
     */
    private String message;

    FlowableCode(int code, String message){
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
