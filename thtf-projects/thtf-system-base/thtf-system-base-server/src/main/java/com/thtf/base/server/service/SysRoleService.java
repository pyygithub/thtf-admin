package com.thtf.base.server.service;

import com.thtf.base.api.vo.SysRoleQueryConditionVO;
import com.thtf.base.api.vo.SysRoleSaveOrUpdateVO;
import com.thtf.base.api.vo.SysRoleVO;
import com.thtf.common.core.response.Pager;

import java.util.List;

/**
 * ---------------------------
 * 角色 (SysRoleService)         
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020-01-09 14:55:26
 * 版本：  v1.0
 * ---------------------------
 */
public interface SysRoleService {

    /**
     * 角色保存
     * @param sysRoleSaveOrUpdateVO
     */
    SysRoleVO save(SysRoleSaveOrUpdateVO sysRoleSaveOrUpdateVO);

	/**
     * 角色修改
     * @param id
     * @param sysRoleSaveOrUpdateVO
     */
    SysRoleVO update(String id, SysRoleSaveOrUpdateVO sysRoleSaveOrUpdateVO);

    /**
     * 角色删除
     * @param id
     */
	void delete(String id);

    /**
     * 批量删除角色
     * @param ids
     */
    void deleteBatch(List<String> ids);

    /**
     * 根据角色ID查询
     * @param id
     */
	SysRoleVO findById(String id);

    /**
     * 角色模糊查询
     * @param queryConditionVO
     * @return
     */
    List<SysRoleVO> findList(SysRoleQueryConditionVO queryConditionVO);

    /**
     * 角色分页模糊查询
     * @param queryConditionVO
     * @param page
     * @param size
     * @return
     */
    Pager<SysRoleVO> findList(SysRoleQueryConditionVO queryConditionVO, int page, int size);

    /**
     * 分配权限
     * @param id
     * @param menuIds
     */
    void setPermissions(String id, List<String> menuIds);
}
