package com.thtf.base.api;

import com.thtf.base.api.vo.SysLogQueryConditionVO;
import com.thtf.base.api.vo.SysLogSaveOrUpdateVO;
import com.thtf.base.api.vo.SysLogVO;
import com.thtf.common.core.response.Pager;
import com.thtf.common.core.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * ---------------------------
 * 操作日志ControllerApi接口
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019/12/27 11:55
 * 版本：  v1.0
 * ---------------------------
 */
@Api(tags="操作日志管理")
public interface SysLogControllerApi {

    @ApiOperation("根据ID查询日志详情")
    @ApiImplicitParam(name = "logId", value = "日志ID", required = true, dataType = "String", paramType = "path")
    @GetMapping("/sysLog/{logId}")
    ResponseResult<SysLogVO> findById(@PathVariable("logId") String logId);

    @ApiOperation("分页查询日志列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name="page",value = "页码", required=true, paramType="query", dataType="int"),
            @ApiImplicitParam(name="size",value = "每页记录数", required=true, paramType="query", dataType="int")
    })
    @GetMapping("/sysLog/page")
    ResponseResult<Pager<SysLogVO>> findList(@RequestParam("page")int page, @RequestParam("size")int size, SysLogQueryConditionVO queryConditionVO);

    @ApiOperation("保存日志信息")
    @PostMapping("/sysLog")
    ResponseResult<SysLogVO> add(@RequestBody SysLogSaveOrUpdateVO sysLogSaveOrUpdateVO);

    @ApiOperation("根据id删除日志")
    @ApiImplicitParam(name = "logId", value = "日志ID", required = true, dataType = "String", paramType = "path")
    @DeleteMapping("/sysLog/{logId}")
    ResponseResult delete(@PathVariable("logId") String logId);
}
