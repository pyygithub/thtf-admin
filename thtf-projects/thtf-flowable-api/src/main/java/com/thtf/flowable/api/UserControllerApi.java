package com.thtf.flowable.api;

import com.thtf.common.core.response.ResponseResult;
import com.thtf.flowable.vo.UserVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ---------------------------
 * 用户管理Controller接口
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020-07-13 10:27:35
 * 版本：  v1.0
 * ---------------------------
 */
@Api(tags="用户管理")
public interface UserControllerApi {

    String PATH_PREFIX = "/user";

    /**
     * 保存用户
     *
     * @param record
     * @return
     */
    @ApiOperation(value = "保存用户", notes = "创建新用户")
    @ApiImplicitParam(name = "record", value = "用户对象", required = true, dataType = "UserVO", paramType = "body")
    @PostMapping(PATH_PREFIX)
    ResponseResult save(@Validated @RequestBody UserVO record);

    /**
     * 修改用户
     *
     * @param id
     * @param record
     * @return
     */
    @ApiOperation(value = "修改用户", notes = "根据ID修改用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "record", value = "用户对象", required = true, dataType = "UserVO", paramType = "body")
    })
    @PutMapping(PATH_PREFIX + "/{id}")
    ResponseResult update(@Validated @PathVariable(value = "id") String id, @RequestBody UserVO record);

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除用户", notes = "根据ID用户")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String", paramType = "path")
    @DeleteMapping(PATH_PREFIX + "/{id}")
    ResponseResult delete(@Validated @PathVariable(value = "id") String id);

    /**
     * 单个用户查询
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "用户查询", notes = "根据ID用户查询")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String", paramType = "path")
    @GetMapping(PATH_PREFIX + "/{id}")
    ResponseResult<UserVO> findById(@Validated @PathVariable("id") String id);

    /**
     * 用户树列表查询
     *
     * @return
     */
    @ApiOperation(value = "用户列表查询", notes = "用户列表查询")
    @GetMapping(PATH_PREFIX + "s")
    ResponseResult<List<UserVO>> getList();
}
