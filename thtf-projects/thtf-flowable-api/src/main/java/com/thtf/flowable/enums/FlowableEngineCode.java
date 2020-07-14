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
public enum FlowableEngineCode implements ResponseCode {
    FLOW_FILE_TYPE_INVALID(50001,"Invalid file name, only .bpmn20.xml files are supported"),
    FLOW_FILE_NOT_FOUND(50002,"No process found in definition！"),
    FLOW_FILE_UPLOAD_FAIL(50003,"Upload file failed！");


    /**
     * 操作代码
     */
    private int code;

    /**
     * 提示信息
     */
    private String message;

    FlowableEngineCode(int code, String message){
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
