package com.thtf.common.core.response;

/**
 * ---------------------------
 * 通用响应状态码
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019/12/26 16:25
 * 版本：  v1.0
 * ---------------------------
 */
public enum CommonCode implements ResponseCode{
    SUCCESS(200,"操作成功！"),
    FAIL(500,"未知异常，请联系管理员！"),


    UNAUTHENTICATED(10001,"此操作需要登陆系统！"),
    UNAUTHORISE(10002,"权限不足，无权操作！"),
    TOKEN_EXPIRED(10003, "token过期"),
    TOKEN_INVALID(10004, "token无效"),
    PERMISSION_SIGNATURE_ERROR(10005, "JWT签名失败"),

    VALIDATE_CODE_EXPIRED(40006, "验证码过期"),
    VALIDATE_CODE_INVALID(40007, "验证码错误"),
    USER_ACCOUNT_FORBIDDEN(40008, "账号已停用"),
    USERNAME_IS_NULL(40009,"用户名不能为空"),
    PASSWORD_IS_NULL(40010,"密码不能为空"),
    CODE_IS_NULL(40011,"验证码不能为空"),
    USERNAME_OR_PASSWORD_ERROR(40012, "用户名或密码错误"),
    USERNAME_NOT_EXISTS(40010,"用户名不存在"),

    INVALID_PARAM(40003,"非法参数！"),
    INVALID_REQUEST_METHOD( 40004, "请求方式错误！"),
    HEADER_NOT_EXISTS_CLIENT(40005,"请求头缺失client信息"),
    HEADER_INVALID_CLIENT(40006,"请求头client格式错误"),

    SERVER_ERROR(99999,"抱歉，系统繁忙，请稍后重试！");

    /**
     * 操作代码
     */
    private int code;

    /**
     * 提示信息
     */
    private String message;

    CommonCode(int code, String message){
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
