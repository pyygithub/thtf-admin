package com.thtf.base.api;

import com.thtf.base.api.vo.SysUserQueryConditionVO;
import com.thtf.base.api.vo.SysUserSaveOrUpdateVO;
import com.thtf.base.api.vo.SysUserVO;
import com.thtf.base.api.vo.UserDetailsVO;
import com.thtf.common.core.response.Pager;
import com.thtf.common.core.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * ---------------------------
 * 用户 (SysUserControllerApi)接口
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020-01-10 16:06:25
 * 版本：  v1.0
 * ---------------------------
 */
@Api(tags = "用户管理模块")
public interface SysUserControllerApi {

    /**
     * 保存用户
     * @param record
     * @return
     */
    @ApiOperation(value = "保存用户", notes = "创建新用户")
    @ApiImplicitParam(name = "record", value = "用户对象", required = true, dataType = "SysUserSaveOrUpdateVO", paramType = "body")
    @PostMapping("/sysUser")
    ResponseResult<SysUserVO> save(@Valid @RequestBody SysUserSaveOrUpdateVO record);

    /**
     * 修改用户
     * @param id
     * @param record
     * @return
     */
    @ApiOperation(value = "修改用户", notes = "根据ID修改用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "record", value = "用户对象", required = true, dataType = "SysUserSaveOrUpdateVO", paramType = "body")
    })
    @PutMapping("/sysUser/{id}")
    ResponseResult<SysUserVO> update(@Valid @PathVariable(value = "id") String id, @RequestBody SysUserSaveOrUpdateVO record);

    /**
     * 删除用户
     * @param id
     * @return
     */
	@ApiOperation(value = "删除用户", notes = "根据ID用户")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String", paramType = "path")
    @DeleteMapping("/sysUser/{id}")
    ResponseResult delete(@Valid @PathVariable(value = "id") String id);

    /**
     * 批量删除用户
     * @param ids
     * @return
     */
	@ApiOperation(value = "删除用户", notes = "根据ID用户")
    @ApiImplicitParam(name = "ids", value = "用户IDS", required = true, dataType = "List", paramType = "body")
    @DeleteMapping("/sysUser/delBatch")
    ResponseResult deleteBatch(@RequestBody List<String> ids);

    /**
     * 单个用户查询
     * @param id
     * @return
     */
    @ApiOperation(value = "用户查询", notes = "根据ID用户查询")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String", paramType = "path")
    @GetMapping("/sysUser/{id}")
    ResponseResult<SysUserVO> findById(@Valid @PathVariable("id") String id);

    /**
     * 用户模糊查询
     * @param queryConditionVO
     * @return
     */
    @ApiOperation(value = "用户模糊查询", notes = "用户不带分页模糊查询")
    @GetMapping("/sysUser/list")
    ResponseResult<List<SysUserVO>> getList(SysUserQueryConditionVO queryConditionVO);

    /**
     * 用户分页查询
     * @param queryConditionVO
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "用户分页查询", notes = "用户分页模糊查询")
    @ApiImplicitParams({
             @ApiImplicitParam(name = "page", value = "当前页码", required = true, dataType = "int", paramType = "query"),
             @ApiImplicitParam(name = "size", value = "分页尺寸", required = true, dataType = "int", paramType = "query")
    })
    @GetMapping("/sysUser/page")
    ResponseResult<Pager<SysUserVO>> getPageList(SysUserQueryConditionVO queryConditionVO,
                                                 @RequestParam(value = "page", defaultValue = "1") int page,
                                                 @RequestParam(value = "size", defaultValue = "10") int size);

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    @ApiOperation(value = "根据用户名查询", notes = "根据用户名查询")
    @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "query")
    @GetMapping("/sysUser")
    ResponseResult<UserDetailsVO> findByUsername(@Valid @NotBlank(message = "用户名不能为空") @RequestParam("username") String username);
	
}
