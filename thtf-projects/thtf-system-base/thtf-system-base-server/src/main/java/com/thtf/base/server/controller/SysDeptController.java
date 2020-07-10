package com.thtf.base.server.controller;

import com.thtf.base.api.SysDeptControllerApi;
import com.thtf.base.api.vo.SysDeptSaveOrUpdateVO;
import com.thtf.base.api.vo.SysDeptTreeVO;
import com.thtf.base.server.service.SysDeptService;
import com.thtf.common.core.response.ResponseResult;
import com.thtf.common.log.annotation.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ---------------------------
 * 部门 (DeptController)         
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019-12-31 16:10:54
 * 版本：  v1.0
 * ---------------------------
 */
@RestController
public class SysDeptController implements SysDeptControllerApi {

	@Autowired
	private SysDeptService sysDeptService;

	@Override
    @Log(desc = "添加部门")
    public ResponseResult<SysDeptTreeVO> save(SysDeptSaveOrUpdateVO record) {
        return ResponseResult.SUCCESS(sysDeptService.save(record));
    }

    @Override
    @Log(desc = "修改部门")
    public ResponseResult<SysDeptTreeVO> update(String id, SysDeptSaveOrUpdateVO record) {
        return ResponseResult.SUCCESS(sysDeptService.update(id, record));
    }

    @Override
    @Log(desc = "删除部门")
    public ResponseResult delete(String id) {
        sysDeptService.delete(id);
        return ResponseResult.SUCCESS();
    }

    @Override
    public ResponseResult<SysDeptTreeVO> findById(String id) {
        return ResponseResult.SUCCESS(sysDeptService.findById(id));
    }

    @Override
    public ResponseResult<List<SysDeptTreeVO>> getTreeList() {
        return ResponseResult.SUCCESS(sysDeptService.getTreeList());
    }


}
