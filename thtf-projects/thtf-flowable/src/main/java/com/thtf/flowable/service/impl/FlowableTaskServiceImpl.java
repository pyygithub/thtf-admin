package com.thtf.flowable.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.thtf.common.core.response.Pager;
import com.thtf.flowable.mapper.FlowableTaskMapper;
import com.thtf.flowable.service.FlowableProcessInstanceService;
import com.thtf.flowable.service.FlowableTaskService;
import com.thtf.flowable.vo.ProcessInstanceQueryVO;
import com.thtf.flowable.vo.ProcessInstanceVO;
import com.thtf.flowable.vo.TaskQueryVO;
import com.thtf.flowable.vo.TaskVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FlowableTaskServiceImpl implements FlowableTaskService {

    @Autowired
    private FlowableTaskMapper flowableTaskMapper;

    @Autowired
    private FlowableProcessInstanceService flowableProcessInstanceService;

    @Override
    public Pager<TaskVO> getTodoTaskList(TaskQueryVO taskQueryVO, Integer pageNum, Integer pageSize) {
        Page page = flowableTaskMapper.getTodoTaskList(new Page(pageNum, pageSize), taskQueryVO);

        Pager<TaskVO> pager = new Pager();
        pager.setTotal(page.getTotal());
        pager.setRecords(page.getRecords());
        return pager;
    }

    @Override
    public Pager<TaskVO> getDoneTaskList(TaskQueryVO taskQueryVO, Integer pageNum, Integer pageSize) {
        Page page = flowableTaskMapper.getDnoeTaskList(new Page(pageNum, pageSize), taskQueryVO);

        Pager<TaskVO> pager = new Pager();
        pager.setTotal(page.getTotal());
        pager.setRecords(page.getRecords());
        return pager;
    }

    @Override
    public Pager<ProcessInstanceVO> getSentTaskList(ProcessInstanceQueryVO processInstanceQueryVO, Integer pageNum, Integer pageSize) {
        return flowableProcessInstanceService.getList(processInstanceQueryVO, pageNum, pageSize);
    }

}
