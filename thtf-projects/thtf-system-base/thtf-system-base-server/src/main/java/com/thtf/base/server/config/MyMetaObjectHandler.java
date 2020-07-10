package com.thtf.base.server.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.thtf.common.core.utils.LoginUserUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 自动填充配置
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        String userId = LoginUserUtil.getUserId();
        String username = LoginUserUtil.getUsername();
        log.info("### 当前操作用户： userId={}, username={} ###", userId, username);
        // 创建人ID
        if (metaObject.hasSetter("createId")) {
            this.setFieldValByName("createId", userId , metaObject);
        }
        // 创建人名称
        if (metaObject.hasSetter("createName")) {
            this.setFieldValByName("createName", username , metaObject);
        }
        // 创建时间
        if (metaObject.hasSetter("createTime")) {
            this.setFieldValByName("createTime", new Date(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        String userId = LoginUserUtil.getUserId();
        String username = LoginUserUtil.getUsername();
        log.info("### 当前操作用户： userId={}, username={} ###", userId, username);
        // 修改人ID
        if (metaObject.hasSetter("updateId")) {
            this.setFieldValByName("updateId", userId, metaObject);
        }
        // 修改人名称
        if (metaObject.hasSetter("updateName")) {
            this.setFieldValByName("updateName", username, metaObject);
        }
        // 修改人时间
        if (metaObject.hasSetter("updateTime")) {
            this.setFieldValByName("updateTime", new Date(), metaObject);
        }
    }
}
