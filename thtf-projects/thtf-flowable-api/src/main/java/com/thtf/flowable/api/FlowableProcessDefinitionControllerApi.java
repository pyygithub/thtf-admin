package com.thtf.flowable.api;

import com.thtf.common.core.response.Pager;
import com.thtf.common.core.response.ResponseResult;
import com.thtf.flowable.entity.FlowProcessDefinition;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * ---------------------------
 * 流程定义API
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020-07-14 13:09:13
 * 版本：  v1.0
 * ---------------------------
 */
@Api(tags="流程定义API")
public interface FlowableProcessDefinitionControllerApi {

    String PATH_PREFIX = "/processDefinition";



    /**
     * 流程定义分页模糊列表
     *
     * @param tenantId  租户ID
     * @param key       流程定义标识
     * @param name      流程定义名称
     * @param pageNum   当前页码
     * @param pageSize  分页尺寸
     * @return
     */
    @GetMapping(PATH_PREFIX + "/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tenantId", value = "租户ID",  required = false, dataType = "string", paramType = "header"),
            @ApiImplicitParam(name = "key", value = "流程定义标识", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "流程定义名称",  required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "category", value = "分类",  required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "当前页码",  required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "分页尺寸",  required = true, dataType = "int", paramType = "query"),
    })
    @ApiOperation(value = "流程定义分页模糊列表", notes = "流程定义分页模糊列表")
    ResponseResult<Pager<FlowProcessDefinition>> list(@RequestHeader(required = false, defaultValue = "") String tenantId,
                                                      @RequestParam(required = false, defaultValue = "") String key,
                                                      @RequestParam(required = false, defaultValue = "") String name,
                                                      @RequestParam(required = false, defaultValue = "") String category,
                                                      @RequestParam(required = true, defaultValue = "1") Integer pageNum,
                                                      @RequestParam(required = true, defaultValue = "10") Integer pageSize);

}