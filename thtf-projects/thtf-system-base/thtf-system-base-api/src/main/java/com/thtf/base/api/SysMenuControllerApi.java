package com.thtf.base.api;

import com.thtf.base.api.vo.*;
import com.thtf.common.core.response.Pager;
import com.thtf.common.core.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * ---------------------------
 * 菜单 (SysMenuControllerApi)接口
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020-01-07 11:26:48
 * 版本：  v1.0
 * ---------------------------
 */
@Api(tags = "菜单管理模块")
public interface SysMenuControllerApi {

    /**
     * 保存菜单
     * @param record
     * @return
     */
    @ApiOperation(value = "保存菜单", notes = "创建新菜单")
    @ApiImplicitParam(name = "record", value = "菜单对象", required = true, dataType = "SysMenuSaveOrUpdateVO", paramType = "body")
    @PostMapping("/sysMenu")
    ResponseResult<SysMenuVO> save(@Valid @RequestBody SysMenuSaveOrUpdateVO record);

    /**
     * 修改菜单
     * @param id
     * @param record
     * @return
     */
    @ApiOperation(value = "修改菜单", notes = "根据ID修改菜单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "菜单ID", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "record", value = "菜单对象", required = true, dataType = "SysMenuSaveOrUpdateVO", paramType = "body")
    })
    @PutMapping("/sysMenu/{id}")
    ResponseResult<SysMenuVO> update(@Valid @PathVariable(value = "id") String id, @RequestBody SysMenuSaveOrUpdateVO record);

    /**
     * 删除菜单
     * @param id
     * @return
     */
	@ApiOperation(value = "删除菜单", notes = "根据ID菜单")
    @ApiImplicitParam(name = "id", value = "菜单ID", required = true, dataType = "String", paramType = "path")
    @DeleteMapping("/sysMenu/{id}")
    ResponseResult delete(@Valid @PathVariable(value = "id") String id);

    /**
     * 单个菜单查询
     * @param id
     * @return
     */
    @ApiOperation(value = "菜单查询", notes = "根据ID菜单查询")
    @ApiImplicitParam(name = "id", value = "菜单ID", required = true, dataType = "String", paramType = "path")
    @GetMapping("/sysMenu/{id}")
    ResponseResult<SysMenuVO> findById(@Valid @PathVariable("id") String id);

    /**
     * 菜单树列表查询
     * @param queryConditionVO
     * @return
     */
    @ApiOperation(value = "菜单树列表查询", notes = "菜单树列表查询")
    @GetMapping("/sysMenu/treeList")
    ResponseResult<List<SysMenuTreeVO>> getList(SysMenuQueryConditionVO queryConditionVO);

    /**
     * 菜单树路由列表查询
     * @return
     */
    @ApiOperation(value = "菜单树路由列表查询", notes = "菜单树路由列表查询")
    @GetMapping("/sysMenu/routeMenus")
    ResponseResult<List<SysMenuRouteVO>> getRouteMenus(HttpServletRequest request);

}
