package com.thtf.flowable.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.thtf.flowable.vo.TaskQueryVO;
import com.thtf.flowable.vo.TaskVO;
import org.apache.ibatis.annotations.Param;

public interface FlowableTaskMapper {
    /**
     * 查询待办任务
     *
     * @param taskQueryVO 参数
     * @return
     */
    Page<TaskVO> getTodoTaskList(IPage<TaskVO> page, @Param("query")TaskQueryVO taskQueryVO) ;

    /**
     * 查询已办任务列表
     *
     * @param taskQueryVO 参数
     * @return
     */
    Page<TaskVO> getDnoeTaskList(IPage<TaskVO> page, @Param("query")TaskQueryVO taskQueryVO) ;

}
