package com.thtf.flowable.utils;

import org.flowable.idm.api.User;
import org.flowable.ui.common.model.RemoteUser;

public class UserUtil {
    public static User getCurrentUser() {
        //TODO 待完善
        RemoteUser remoteUser = new RemoteUser();
        remoteUser.setId("admin");
        remoteUser.setDisplayName("测试用户");
        return remoteUser;
    }
}
