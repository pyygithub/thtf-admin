package com.thtf.flowable.controller;

import com.thtf.common.core.exception.BusinessException;
import com.thtf.common.core.response.CommonCode;
import com.thtf.common.core.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserController {

    @GetMapping("/test")
    public ResponseResult test() {
        if (true) {
            log.error("手动阀发斯蒂芬");
            throw new BusinessException(CommonCode.VALIDATE_CODE_EXPIRED);
        }
        return ResponseResult.SUCCESS();
    }
}
