package com.thtf.base.server.controller;

import com.thtf.base.api.SysJobControllerApi;
import com.thtf.base.api.vo.SysJobQueryConditionVO;
import com.thtf.base.api.vo.SysJobSaveOrUpdateVO;
import com.thtf.base.api.vo.SysJobVO;
import com.thtf.base.server.service.SysJobService;
import com.thtf.common.core.response.Pager;
import com.thtf.common.core.response.ResponseResult;
import com.thtf.common.log.annotation.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * ---------------------------
 * 岗位 (SysJobController)         
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020-01-03 16:01:11
 * 版本：  v1.0
 * ---------------------------
 */
@RestController
public class SysJobController implements SysJobControllerApi {

	@Autowired
	private SysJobService sysJobService;

	@Override
    @Log(desc = "添加岗位")
    public ResponseResult<SysJobVO> save(SysJobSaveOrUpdateVO record) {
        return ResponseResult.SUCCESS(sysJobService.save(record));
    }

    @Override
    @Log(desc = "修改岗位")
    public ResponseResult<SysJobVO> update(String id, SysJobSaveOrUpdateVO record) {
        return ResponseResult.SUCCESS(sysJobService.update(id, record));
    }

    @Override
    @Log(desc = "删除岗位")
    public ResponseResult delete(String id) {
        sysJobService.delete(id);
        return ResponseResult.SUCCESS();
    }

    @Override
    @Log(desc = "批量删除岗位")
    public ResponseResult deleteBatch(@Valid List<String> ids) {
        sysJobService.deleteBatch(ids);
        return ResponseResult.SUCCESS();
    }

    @Override
    public ResponseResult<SysJobVO> findById(String id) {
        return ResponseResult.SUCCESS(sysJobService.findById(id));
    }

    @Override
    public ResponseResult<List<SysJobVO>> getList(SysJobQueryConditionVO queryConditionVO) {
        return ResponseResult.SUCCESS(sysJobService.findList(queryConditionVO));
    }

    @Override
    public ResponseResult<Pager<SysJobVO>> getPageList(SysJobQueryConditionVO queryConditionVO, int page, int size) {
        return ResponseResult.SUCCESS(sysJobService.findList(queryConditionVO, page, size));
    }
	
}
