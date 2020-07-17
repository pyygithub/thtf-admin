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
    FLOW_FILE_TYPE_INVALID(50001,"无效文件(支持文件格式 .bpmn20.xml)"),
    FLOW_FILE_NOT_FOUND(50002,"流程文件不存在！"),
    FLOW_FILE_UPLOAD_FAIL(50003,"上传失败！"),
    FLOW_DEPLOY_FAIL(50004,"部署失败！"),
    FLOW_GENERATE_BPMN_FAIL(50005,"生成 BPMN 2.0 model失败！"),
    FLOW_MODEL_NOT_FOUND(50006,"流程模板不存在！"),
    FLOW_DELETE_FAIL(50007,"流程模板删除失败!"),
    FLOW_SUSPENDED(50008,"此流程已经挂起,请联系系统管理员!"),
    FLOW_DEFINITION_NO_FOUNED(50009,"流程定义不存在!"),
    FLOW_INSTANCE_NO_FOUNED(50010,"流程实例不存在!");


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
