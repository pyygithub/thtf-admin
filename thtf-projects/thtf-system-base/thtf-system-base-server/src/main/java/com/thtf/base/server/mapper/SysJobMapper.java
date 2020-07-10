package com.thtf.base.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.thtf.base.api.entity.SysJob;
import com.thtf.base.api.vo.SysJobQueryConditionVO;
import com.thtf.base.api.vo.SysJobVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ---------------------------
 * 岗位 (SysJobMapper)         
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020-01-03 16:01:11
 * 版本：  v1.0
 * ---------------------------
 */
public interface SysJobMapper extends BaseMapper<SysJob>{
    List<SysJobVO> selectJobDeptList(@Param("queryConditionVO")SysJobQueryConditionVO queryConditionVO);

    List<SysJobVO> selectJobDeptList(Page<SysJobVO> page, @Param("queryConditionVO")SysJobQueryConditionVO queryConditionVO);
}