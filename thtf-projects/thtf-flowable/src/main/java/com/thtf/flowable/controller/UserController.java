package com.thtf.flowable.controller;

import com.thtf.common.core.response.ResponseResult;
import com.thtf.flowable.api.UserControllerApi;
import com.thtf.flowable.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class UserController implements UserControllerApi {


    @Override
    public ResponseResult save(UserVO record) {
        return ResponseResult.SUCCESS();
    }

    @Override
    public ResponseResult update(String id, UserVO record) {
        return ResponseResult.SUCCESS();
    }

    @Override
    public ResponseResult delete(String id) {
        return ResponseResult.SUCCESS();
    }

    @Override
    public ResponseResult<UserVO> findById(String id) {
        return ResponseResult.SUCCESS();
    }

    @Override
    public ResponseResult<List<UserVO>> getList() {
        return ResponseResult.SUCCESS();
    }
}
