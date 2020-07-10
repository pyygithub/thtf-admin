package com.thtf.common.core.enums;

public enum UserStatus {
    OK("0", "正常"),
    DISABLE("1", "停用"),
    DELETED("2", "删除");

    private final String code;
    private final String info;

    private UserStatus(String code, String info) {
        this.code = code;
        this.info = info;
    }

    public String code() {
        return this.code;
    }

    public String info() {
        return this.info;
    }

    public static void main(String[] args) {
        System.out.println(UserStatus.OK);
    }
}
