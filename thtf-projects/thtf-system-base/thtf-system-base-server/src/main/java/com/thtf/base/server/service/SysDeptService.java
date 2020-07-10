package com.thtf.base.server.service;

import com.thtf.base.api.vo.*;
import com.thtf.common.core.response.Pager;

import java.util.List;

/**
 * ---------------------------
 * 部门 (DeptService)         
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019-12-31 16:10:54
 * 版本：  v1.0
 * ---------------------------
 */
public interface SysDeptService {

    /**
     * 部门保存
     * @param sysDeptSaveOrUpdateVO
     */
    SysDeptTreeVO save(SysDeptSaveOrUpdateVO sysDeptSaveOrUpdateVO);

	/**
     * 部门修改
     * @param id
     * @param sysDeptSaveOrUpdateVO
     */
    SysDeptTreeVO update(String id, SysDeptSaveOrUpdateVO sysDeptSaveOrUpdateVO);

    /**
     * 部门删除
     * @param id
     */
    void delete(String id);

    /**
     * 根据部门ID查询
     * @param id
     */
    SysDeptTreeVO findById(String id);

    /**
     * 查询部门树
     * @return
     */
    List<SysDeptTreeVO> getTreeList();
}
