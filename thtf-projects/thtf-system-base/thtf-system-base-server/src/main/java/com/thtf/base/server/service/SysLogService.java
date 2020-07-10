package com.thtf.base.server.service;

import com.thtf.base.api.vo.SysLogQueryConditionVO;
import com.thtf.base.api.vo.SysLogSaveOrUpdateVO;
import com.thtf.base.api.vo.SysLogVO;
import com.thtf.common.core.response.Pager;

/**
 * ---------------------------
 * 系统日志Service接口
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019/12/27 15:44
 * 版本：  v1.0
 * ---------------------------
 */
public interface SysLogService {

    Pager<SysLogVO> findList(int page, int size, SysLogQueryConditionVO queryConditionVO);

    SysLogVO add(SysLogSaveOrUpdateVO sysLogSaveOrUpdateVO);

    void delete(String logId);

    SysLogVO findById(String id);
}
