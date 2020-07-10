package com.thtf.base.api;

import com.thtf.base.api.vo.SysDeptSaveOrUpdateVO;
import com.thtf.base.api.vo.SysDeptTreeVO;
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
 * 部门管理Controller接口
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019/12/31 16:47
 * 版本：  v1.0
 * ---------------------------
 */
@Api(tags="部门管理")
public interface SysDeptControllerApi {
    /**
     * 保存部门
     * @param record
     * @return
     */
    @ApiOperation(value = "保存部门", notes = "创建新部门")
    @ApiImplicitParam(name = "record", value = "部门对象", required = true, dataType = "SysDeptSaveOrUpdateVO", paramType = "body")
    @PostMapping("/sysDept")
    ResponseResult<SysDeptTreeVO> save(@Valid @RequestBody SysDeptSaveOrUpdateVO record);

    /**
     * 修改部门
     * @param id
     * @param record
     * @return
     */
    @ApiOperation(value = "修改部门", notes = "根据ID修改部门")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "部门ID", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "record", value = "部门对象", required = true, dataType = "SysDeptSaveOrUpdateVO", paramType = "body")
    })
    @PutMapping("/sysDept/{id}")
    ResponseResult<SysDeptTreeVO> update(@Valid @PathVariable(value = "id") String id, @RequestBody SysDeptSaveOrUpdateVO record);

    /**
     * 删除部门
     * @param id
     * @return
     */
    @ApiOperation(value = "删除部门", notes = "根据ID部门")
    @ApiImplicitParam(name = "id", value = "部门ID", required = true, dataType = "String", paramType = "path")
    @DeleteMapping("/sysDept/{id}")
    ResponseResult delete(@Valid @PathVariable(value = "id") String id);

    /**
     * 单个部门查询
     * @param id
     * @return
     */
    @ApiOperation(value = "部门查询", notes = "根据ID部门查询")
    @ApiImplicitParam(name = "id", value = "部门ID", required = true, dataType = "String", paramType = "path")
    @GetMapping("/sysDept/{id}")
    ResponseResult<SysDeptTreeVO> findById(@Valid @PathVariable("id") String id);

    /**
     * 部门树列表查询
     * @return
     */
    @ApiOperation(value = "部门树列表查询", notes = "部门树列表查询")
    @GetMapping("/sysDept/treeList")
    ResponseResult<List<SysDeptTreeVO>> getTreeList();
}
