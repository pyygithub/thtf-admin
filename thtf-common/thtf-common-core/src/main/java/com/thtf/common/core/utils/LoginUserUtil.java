package com.thtf.common.core.utils;


import com.thtf.common.core.model.ProfileUser;

/**
 * ---------------------------
 * 登录用户上下文
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020/1/13 10:39
 * 版本：  v1.0
 * ---------------------------
 */
public class LoginUserUtil {
    private static final ThreadLocal<ProfileUser> CURRENT_USER = new ThreadLocal<>();

    public static void setCurrentUser(ProfileUser user) {
        CURRENT_USER.set(user);
    }

    public static ProfileUser getCurrentUser() {
        return CURRENT_USER.get();
    }

    public static void remove() {
        CURRENT_USER.remove();
    }

    public static String getUserId() {
        return CURRENT_USER.get().getUserId();
    }

    public static String getUsername() {
        return CURRENT_USER.get().getUsername();
    }
}
