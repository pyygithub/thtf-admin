package com.thtf.base.server.controller;

import com.thtf.base.api.SysRoleControllerApi;
import com.thtf.base.api.vo.SysRoleQueryConditionVO;
import com.thtf.base.api.vo.SysRoleSaveOrUpdateVO;
import com.thtf.base.api.vo.SysRoleVO;
import com.thtf.base.server.service.SysRoleService;
import com.thtf.common.core.response.Pager;
import com.thtf.common.core.response.ResponseResult;
import com.thtf.common.log.annotation.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ---------------------------
 * 角色 (SysRoleController)         
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020-01-09 14:55:26
 * 版本：  v1.0
 * ---------------------------
 */
@RestController
public class SysRoleController implements SysRoleControllerApi {

	@Autowired
	private SysRoleService sysRoleService;

    @Override
    @Log(desc = "添加角色")
    public ResponseResult<SysRoleVO> save(SysRoleSaveOrUpdateVO record) {
        return ResponseResult.SUCCESS(sysRoleService.save(record));
    }

    @Override
    @Log(desc = "修改角色")
    public ResponseResult<SysRoleVO> update(String id, SysRoleSaveOrUpdateVO record) {
        return ResponseResult.SUCCESS(sysRoleService.update(id, record));
    }

    @Override
    @Log(desc = "删除角色")
    public ResponseResult delete(String id) {
        sysRoleService.delete(id);
        return ResponseResult.SUCCESS();
    }

    @Override
    @Log(desc = "批量删除角色")
    public ResponseResult deleteBatch(List<String> ids) {
        sysRoleService.deleteBatch(ids);
        return ResponseResult.SUCCESS();
    }

    @Override
    public ResponseResult<SysRoleVO> findById(String id) {
        return ResponseResult.SUCCESS(sysRoleService.findById(id));
    }

    @Override
    public ResponseResult<List<SysRoleVO>> getList(SysRoleQueryConditionVO queryConditionVO) {
        return ResponseResult.SUCCESS(sysRoleService.findList(queryConditionVO));
    }

    @Override
    public ResponseResult<Pager<SysRoleVO>> getPageList(SysRoleQueryConditionVO queryConditionVO, int page, int size) {
        return ResponseResult.SUCCESS(sysRoleService.findList(queryConditionVO, page, size));
    }

    @Override
    public ResponseResult<SysRoleVO> setPermissions(String id, List<String> menuIds) {
        sysRoleService.setPermissions(id, menuIds);
        return ResponseResult.SUCCESS();
    }

}
