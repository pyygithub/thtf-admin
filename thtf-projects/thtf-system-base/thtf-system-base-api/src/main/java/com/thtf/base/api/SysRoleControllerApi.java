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

import javax.validation.Valid;
import java.util.List;

/**
 * ---------------------------
 * 角色 (SysRoleControllerApi)接口
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020-01-09 14:55:26
 * 版本：  v1.0
 * ---------------------------
 */
@Api(tags = "角色管理模块")
public interface SysRoleControllerApi {

    /**
     * 保存角色
     * @param record
     * @return
     */
    @ApiOperation(value = "保存角色", notes = "创建新角色")
    @ApiImplicitParam(name = "record", value = "角色对象", required = true, dataType = "SysRoleSaveOrUpdateVO", paramType = "body")
    @PostMapping("/sysRole")
    ResponseResult<SysRoleVO> save(@Valid @RequestBody SysRoleSaveOrUpdateVO record);

    /**
     * 修改角色
     * @param id
     * @param record
     * @return
     */
    @ApiOperation(value = "修改角色", notes = "根据ID修改角色")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色ID", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "record", value = "角色对象", required = true, dataType = "SysRoleSaveOrUpdateVO", paramType = "body")
    })
    @PutMapping("/sysRole/{id}")
    ResponseResult<SysRoleVO> update(@Valid @PathVariable(value = "id") String id, @RequestBody SysRoleSaveOrUpdateVO record);

    /**
     * 删除角色
     * @param id
     * @return
     */
	@ApiOperation(value = "删除角色", notes = "根据ID角色")
    @ApiImplicitParam(name = "id", value = "角色ID", required = true, dataType = "String", paramType = "path")
    @DeleteMapping("/sysRole/{id}")
    ResponseResult delete(@Valid @PathVariable(value = "id") String id);

    /**
     * 批量删除角色
     * @param ids
     * @return
     */
    @ApiOperation(value = "批量删除角色", notes = "批量删除角色")
    @ApiImplicitParam(name = "ids", value = "角色IDS", required = true, dataType = "List", paramType = "body")
    @DeleteMapping("/sysRole/delBatch")
    ResponseResult deleteBatch(@RequestBody List<String> ids);

    /**
     * 单个角色查询
     * @param id
     * @return
     */
    @ApiOperation(value = "角色查询", notes = "根据ID角色查询")
    @ApiImplicitParam(name = "id", value = "角色ID", required = true, dataType = "String", paramType = "path")
    @GetMapping("/sysRole/{id}")
    ResponseResult<SysRoleVO> findById(@Valid @PathVariable("id") String id);

    /**
     * 角色模糊查询
     * @param queryConditionVO
     * @return
     */
    @ApiOperation(value = "角色模糊查询", notes = "角色不带分页模糊查询")
    @GetMapping("/sysRole/list")
    ResponseResult<List<SysRoleVO>> getList(SysRoleQueryConditionVO queryConditionVO);

    /**
     * 角色分页查询
     * @param queryConditionVO
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "角色分页查询", notes = "角色分页模糊查询")
    @ApiImplicitParams({
             @ApiImplicitParam(name = "page", value = "当前页码", required = true, dataType = "int", paramType = "query"),
             @ApiImplicitParam(name = "size", value = "分页尺寸", required = true, dataType = "int", paramType = "query")
    })
    @GetMapping("/sysRole/page")
    ResponseResult<Pager<SysRoleVO>> getPageList(SysRoleQueryConditionVO queryConditionVO,
                                                 @RequestParam(value = "page", defaultValue = "1") int page,
                                                 @RequestParam(value = "size", defaultValue = "10") int size);

    /**
     * 角色授权
     * @param id
     * @param menuIds
     * @return
     */
    @ApiOperation(value = "角色授权", notes = "角色授权")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "角色ID", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "menuIds", value = "菜单ID集合", required = true, dataType = "List", paramType = "body")
    })
    @PutMapping("/sysRole/{id}/permissions")
    ResponseResult<SysRoleVO> setPermissions(@Valid @PathVariable(value = "id") String id, @RequestBody List<String> menuIds);
	
}
