package com.thtf.common.core.constant;

/**
 * ---------------------------
 * 日志常量
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019/12/27 17:24
 * 版本：  v1.0
 * ---------------------------
 */
public interface LogConstant {

    /**
     * 操作日志类型： 查询/获取
     */
    public static final int OPERATE_TYPE_QUERY = 1;

    /**
     * 操作日志类型： 添加
     */
    public static final int OPERATE_TYPE_ADD = 2;

    /**
     * 操作日志类型： 更新
     */
    public static final int OPERATE_TYPE_UPDATE = 3;

    /**
     * 操作日志类型： 删除
     */
    public static final int OPERATE_TYPE_DELETE = 4;


    /**
     *  操作记录
     */
    public static final int LOG_TYPE_OPERATION = 1;

    /**
     *  异常记录
     */
    public static final int LOG_TYPE_EXCEPTION = 2;

}
