package com.thtf.flowable.api;

import com.thtf.common.core.response.Pager;
import com.thtf.common.core.response.ResponseResult;
import com.thtf.common.core.validate.EnumValue;
import com.thtf.flowable.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * ---------------------------
 * 任务管理API
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020-07-14 13:09:13
 * 版本：  v1.0
 * ---------------------------
 */
@Api(tags="任务管理API")
public interface FlowableTaskControllerApi {

    String PATH_PREFIX = "/task";


    /**
     * 获取待办任务列表
     *
     * @param taskQueryVO   查询条件
     * @param pageNum       当前页码
     * @param pageSize      分页尺寸
     * @return
     */
    @GetMapping(PATH_PREFIX + "/todo-list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码",  required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "分页尺寸",  required = true, dataType = "int", paramType = "query"),
    })
    @ApiOperation(value = "获取待办任务列表", notes = "获取待办任务分页列表")
    ResponseResult<Pager<TaskVO>> todoTasklistPage(
            TaskQueryVO taskQueryVO,
            @RequestParam(required = true, defaultValue = "1") Integer pageNum,
            @RequestParam(required = true, defaultValue = "10") Integer pageSize);

    /**
     * 获取已办/办结任务列表
     *
     * @param taskQueryVO   查询条件
     * @param pageNum       当前页码
     * @param pageSize      分页尺寸
     * @return
     */
    @GetMapping(PATH_PREFIX + "/done-list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码",  required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "分页尺寸",  required = true, dataType = "int", paramType = "query"),
    })
    @ApiOperation(value = "获取已办/办结任务列表", notes = "获取已办/办结任务列表")
    ResponseResult<Pager<TaskVO>> doneTasklistPage(
            TaskQueryVO taskQueryVO,
            @RequestParam(required = true, defaultValue = "1") Integer pageNum,
            @RequestParam(required = true, defaultValue = "10") Integer pageSize);

    /**
     * 获取已发任务列表
     *
     * @param processInstanceQueryVO   查询条件
     * @param pageNum                   当前页码
     * @param pageSize                  分页尺寸
     * @return
     */
    @GetMapping(PATH_PREFIX + "/sent-list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码",  required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "分页尺寸",  required = true, dataType = "int", paramType = "query"),
    })
    @ApiOperation(value = "获取已发任务列表", notes = "获取已发任务列表")
    ResponseResult<Pager<ProcessInstanceVO>> sentTasklistPage(
            ProcessInstanceQueryVO processInstanceQueryVO,
            @RequestParam(required = true, defaultValue = "1") Integer pageNum,
            @RequestParam(required = true, defaultValue = "10") Integer pageSize);
}
