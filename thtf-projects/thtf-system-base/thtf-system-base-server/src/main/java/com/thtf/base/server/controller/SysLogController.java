package com.thtf.base.server.controller;

import com.thtf.base.api.SysLogControllerApi;
import com.thtf.base.api.vo.SysLogQueryConditionVO;
import com.thtf.base.api.vo.SysLogSaveOrUpdateVO;
import com.thtf.base.api.vo.SysLogVO;
import com.thtf.base.server.service.SysLogService;
import com.thtf.common.core.response.Pager;
import com.thtf.common.core.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * ---------------------------
 * 系统操作日志记录Controller
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019/12/27 15:43
 * 版本：  v1.0
 * ---------------------------
 */
@RestController
public class SysLogController implements SysLogControllerApi {

    @Autowired
    private SysLogService sysLogService;

    @Override
    public ResponseResult<SysLogVO> findById(String logId) {
        return ResponseResult.SUCCESS(sysLogService.findById(logId));
    }

    @Override
    public ResponseResult<Pager<SysLogVO>> findList(int page, int size, SysLogQueryConditionVO queryConditionVO) {
        return ResponseResult.SUCCESS(sysLogService.findList(page, size, queryConditionVO));
    }

    @Override
    public ResponseResult<SysLogVO> add(SysLogSaveOrUpdateVO sysLogSaveOrUpdateVO) {
        return ResponseResult.SUCCESS(sysLogService.add(sysLogSaveOrUpdateVO));
    }
    @Override
    public ResponseResult delete(String logId) {
        sysLogService.delete(logId);
        return ResponseResult.SUCCESS();
    }
}
