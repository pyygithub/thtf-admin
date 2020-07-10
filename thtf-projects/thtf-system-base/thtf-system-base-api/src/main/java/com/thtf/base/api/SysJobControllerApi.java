package com.thtf.base.api;

import com.thtf.base.api.vo.SysJobQueryConditionVO;
import com.thtf.base.api.vo.SysJobSaveOrUpdateVO;
import com.thtf.base.api.vo.SysJobVO;
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
 * 岗位管理Controller接口
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019/12/31 16:47
 * 版本：  v1.02
 * ---------------------------
 */
@Api(tags="岗位管理")
public interface SysJobControllerApi {
    /**
     * 保存岗位
     * @param record
     * @return
     */
    @ApiOperation(value = "保存岗位", notes = "创建新岗位")
    @ApiImplicitParam(name = "record", value = "岗位对象", required = true, dataType = "SysJobSaveOrUpdateVO", paramType = "body")
    @PostMapping("/sysJob")
    ResponseResult<SysJobVO> save(@Valid @RequestBody SysJobSaveOrUpdateVO record);

    /**
     * 修改岗位
     * @param id
     * @param record
     * @return
     */
    @ApiOperation(value = "修改岗位", notes = "根据ID修改岗位")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "岗位ID", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "record", value = "岗位对象", required = true, dataType = "SysJobSaveOrUpdateVO", paramType = "body")
    })
    @PutMapping("/sysJob/{id}")
    ResponseResult<SysJobVO> update(@Valid @PathVariable(value = "id") String id, @RequestBody SysJobSaveOrUpdateVO record);

    /**
     * 删除岗位
     * @param id
     * @return
     */
    @ApiOperation(value = "删除岗位", notes = "根据ID删除岗位")
    @ApiImplicitParam(name = "id", value = "岗位ID", required = true, dataType = "String", paramType = "path")
    @DeleteMapping("/sysJob/{id}")
    ResponseResult delete(@Valid @PathVariable(value = "id") String id);

    /**
     * 批量删除岗位
     * @param ids
     * @return
     */
    @ApiOperation(value = "批量删除岗位", notes = "批量删除岗位")
    @ApiImplicitParam(name = "ids", value = "岗位IDS", required = true, dataType = "List", paramType = "body")
    @DeleteMapping("/sysJob/delBatch")
    ResponseResult deleteBatch(@Valid @RequestBody List<String> ids);

    /**
     * 单个岗位查询
     * @param id
     * @return
     */
    @ApiOperation(value = "岗位查询", notes = "根据ID岗位查询")
    @ApiImplicitParam(name = "id", value = "岗位ID", required = true, dataType = "String", paramType = "path")
    @GetMapping("/sysJob/{id}")
    ResponseResult<SysJobVO> findById(@Valid @PathVariable("id") String id);

    /**
     * 岗位模糊查询
     * @param queryConditionVO
     * @return
     */
    @ApiOperation(value = "岗位模糊查询", notes = "岗位不带分页模糊查询")
    @GetMapping("/sysJob/list")
    ResponseResult<List<SysJobVO>> getList(SysJobQueryConditionVO queryConditionVO);

    /**
     * 岗位分页查询
     * @param queryConditionVO
     * @param page
     * @param size
     * @return
     */
    @ApiOperation(value = "岗位分页查询", notes = "岗位分页模糊查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页码", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "分页尺寸", required = true, dataType = "int", paramType = "query")
    })
    @GetMapping("/sysJob/page")
    ResponseResult<Pager<SysJobVO>> getPageList(SysJobQueryConditionVO queryConditionVO,
                                                @RequestParam(value = "page", defaultValue = "1") int page,
                                                @RequestParam(value = "size", defaultValue = "10") int size);
}
