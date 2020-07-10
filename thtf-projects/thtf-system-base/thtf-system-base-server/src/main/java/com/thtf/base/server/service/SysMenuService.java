package com.thtf.base.server.service;

import com.thtf.base.api.vo.*;
import com.thtf.common.core.response.Pager;
import com.thtf.common.core.response.ResponseResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * ---------------------------
 * 菜单 (SysMenuService)         
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020-01-07 11:13:01
 * 版本：  v1.0
 * ---------------------------
 */
public interface SysMenuService {

    /**
     * 菜单保存
     * @param sysMenuSaveOrUpdateVO
     */
    SysMenuVO save(SysMenuSaveOrUpdateVO sysMenuSaveOrUpdateVO);

	/**
     * 菜单修改
     * @param id
     * @param sysMenuSaveOrUpdateVO
     */
    SysMenuVO update(String id, SysMenuSaveOrUpdateVO sysMenuSaveOrUpdateVO);

    /**
     * 菜单删除
     * @param id
     */
	void delete(String id);

    /**
     * 根据菜单ID查询
     * @param id
     */
	SysMenuVO findById(String id);

    /**
     * 菜单模糊查询
     * @param queryConditionVO
     * @return
     */
    List<SysMenuVO> findList(SysMenuQueryConditionVO queryConditionVO);

    /**
     * 菜单分页模糊查询
     * @param queryConditionVO
     * @param page
     * @param size
     * @return
     */
    Pager<SysMenuVO> findList(SysMenuQueryConditionVO queryConditionVO, int page, int size);

    /**
     * 菜单树列表查询
     * @param queryConditionVO
     * @return
     */
    List<SysMenuTreeVO> findTreeList(SysMenuQueryConditionVO queryConditionVO);

    /**
     * 菜单路由列表查询
     * @return
     */
    List<SysMenuRouteVO> getRouteMenus(HttpServletRequest request);
}
