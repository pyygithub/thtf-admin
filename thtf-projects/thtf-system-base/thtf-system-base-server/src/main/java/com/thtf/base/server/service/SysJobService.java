package com.thtf.base.server.service;

import com.thtf.base.api.vo.SysJobQueryConditionVO;
import com.thtf.base.api.vo.SysJobSaveOrUpdateVO;
import com.thtf.base.api.vo.SysJobVO;
import com.thtf.common.core.response.Pager;

import java.util.List;

/**
 * ---------------------------
 * 岗位 (SysJobService)         
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020-01-03 16:01:11
 * 版本：  v1.0
 * ---------------------------
 */
public interface SysJobService {

    /**
     * 岗位保存
     * @param sysJobSaveOrUpdateVO
     */
    SysJobVO save(SysJobSaveOrUpdateVO sysJobSaveOrUpdateVO);

	/**
     * 岗位修改
     * @param id
     * @param sysJobSaveOrUpdateVO
     */
    SysJobVO update(String id, SysJobSaveOrUpdateVO sysJobSaveOrUpdateVO);

    /**
     * 岗位删除
     * @param id
     */
	void delete(String id);

    /**
     * 根据岗位ID查询
     * @param id
     */
	SysJobVO findById(String id);

    /**
     * 岗位模糊查询
     * @param queryConditionVO
     * @return
     */
    List<SysJobVO> findList(SysJobQueryConditionVO queryConditionVO);

    /**
     * 岗位分页模糊查询
     * @param queryConditionVO
     * @param page
     * @param size
     * @return
     */
    Pager<SysJobVO> findList(SysJobQueryConditionVO queryConditionVO, int page, int size);

    /**
     * 批量删除岗位信息
     * @param ids
     */
    void deleteBatch(List<String> ids);
}
