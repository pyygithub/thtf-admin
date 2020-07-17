package com.thtf.flowable.api;

import com.thtf.common.core.response.Pager;
import com.thtf.common.core.response.ResponseResult;
import com.thtf.common.core.validate.EnumValue;
import com.thtf.flowable.vo.EndProcessInstanceVO;
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
    ResponseResult startProcessInstance(@Validated @RequestBody StartProcessInstanceVO startProcessInstanceVO);

    /**
     * 终止流程实例
     * @param endProcessInstanceVO
     * @return
     */
    @PostMapping(PATH_PREFIX + "/end")
    @ApiOperation(value = "终止流程实例", notes = "终止流程实例")
    ResponseResult endProcessInstance(@Validated @RequestBody EndProcessInstanceVO endProcessInstanceVO);

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

    /**
     * 激活或挂起流程实例
     *
     * @param processInstanceId   流程实例ID
     * @return
     */
    @PostMapping(PATH_PREFIX + "/activate")
    @ApiOperation(value = "激活或挂起流程实例", notes = "激活或挂起流程实例")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "流程实例ID", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "1:激活，0:挂起", required = true, dataType = "int", paramType = "query"),
    })
    ResponseResult suspendOrActivateByProcessInstanceId(@RequestParam(value = "id") String processInstanceId, @EnumValue(intValues={1,2}, message = "状态参数错误(1:激活,2:挂起)") @RequestParam Integer status);

    /**
     * 删除流程实例
     *
     * @param processInstanceId   流程实例ID
     * @return
     */
    @DeleteMapping(PATH_PREFIX + "/{id}")
    @ApiOperation(value = "删除流程实例", notes = "删除流程实例")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "流程实例ID", required = true, dataType = "string", paramType = "path"),
    })
    ResponseResult deleteByProcessInstanceId(@PathVariable(value = "id") String processInstanceId);

}
