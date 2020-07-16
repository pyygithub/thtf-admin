package com.thtf.flowable.api;

import com.thtf.common.core.response.Pager;
import com.thtf.common.core.response.ResponseResult;
import com.thtf.flowable.vo.ProcessInstanceQueryVO;
import com.thtf.flowable.vo.ProcessInstanceVO;
import com.thtf.flowable.vo.StartProcessInstanceVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * ---------------------------
 * 流程实例API
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020-07-14 13:09:13
 * 版本：  v1.0
 * ---------------------------
 */
@Api(tags="流程实例API")
public interface FlowableProcessInstanceControllerApi {

    String PATH_PREFIX = "/process/instance";


    /**
     * 启动流程实例
     * @param startProcessInstanceVO
     * @return
     */
    @PostMapping(PATH_PREFIX + "/start")
    @ApiOperation(value = "启动流程实例", notes = "启动流程实例")
    ResponseResult startProcess(@Validated @RequestBody StartProcessInstanceVO startProcessInstanceVO);

    /**
     * 流程实例分页模糊列表
     *
     * @param pageNum   当前页码
     * @param pageSize  分页尺寸
     * @return
     */
    @GetMapping(PATH_PREFIX + "/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页码",  required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "分页尺寸",  required = true, dataType = "int", paramType = "query"),
    })
    @ApiOperation(value = "流程实例列表查询", notes = "流程实例分页模糊列表")
    ResponseResult<Pager<ProcessInstanceVO>> listPage(
            ProcessInstanceQueryVO processInstanceQueryVO,
            @RequestParam(required = true, defaultValue = "1") Integer pageNum,
            @RequestParam(required = true, defaultValue = "10") Integer pageSize);

    /**
     * 流程实例追踪
     *
     * @param processInstanceId
     * @return
     */
    @GetMapping(PATH_PREFIX + "/imageTrack/{processInstanceId}")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "processInstanceId", value = "流程实例ID", required = true, dataType = "string", paramType = "path"),
    })
    @ApiOperation(value = "流程实例追踪", notes = "流程实例追踪")
    void processInstanceImageTrack(@PathVariable String processInstanceId, HttpServletResponse response);
}
