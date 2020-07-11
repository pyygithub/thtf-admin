package com.thtf.flowable.controller;

import com.thtf.common.core.response.ResponseResult;
import com.thtf.flowable.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "用户管理相关接口")
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @PostMapping
    @ApiOperation("添加用户")
    public ResponseResult addUser(@Validated @RequestBody UserVO user) {
        log.info("add user:{}", user);
        return ResponseResult.SUCCESS();
    }

    @GetMapping("/{id}")
    @ApiOperation("根据id查询用户的接口")
    @ApiImplicitParam(name = "id", value = "用户id", dataType = "string", required = true, paramType = "path")
    public ResponseResult addUser(@PathVariable String id) {
        log.info("get user by id:{}", id);
        return ResponseResult.SUCCESS();
    }
}
