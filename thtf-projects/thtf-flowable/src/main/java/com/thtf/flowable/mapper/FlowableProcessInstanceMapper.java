package com.thtf.flowable.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.thtf.flowable.vo.ProcessInstanceQueryVO;
import com.thtf.flowable.vo.ProcessInstanceVO;
import org.apache.ibatis.annotations.Param;

public interface FlowableProcessInstanceMapper {

    /**
     * 自定义分页查询
     * @param processInstanceQueryVO
     * @return
     */
    Page<ProcessInstanceVO> selectList(IPage<ProcessInstanceVO> page, @Param("query")ProcessInstanceQueryVO processInstanceQueryVO);
}
