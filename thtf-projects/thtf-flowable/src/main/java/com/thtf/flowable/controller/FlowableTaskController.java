package com.thtf.flowable.controller;

import com.thtf.common.core.response.Pager;
import com.thtf.common.core.response.ResponseResult;
import com.thtf.flowable.api.FlowableTaskControllerApi;
import com.thtf.flowable.service.FlowableProcessInstanceService;
import com.thtf.flowable.service.FlowableTaskService;
import com.thtf.flowable.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class FlowableTaskController implements FlowableTaskControllerApi {

    @Autowired
    private FlowableTaskService flowableTaskService;

    @Autowired
    private FlowableProcessInstanceService flowableProcessInstanceService;

    @Override
    public ResponseResult<Pager<TaskVO>> todoTasklistPage(TaskQueryVO taskQueryVO, Integer pageNum, Integer pageSize) {
        Pager<TaskVO> pager = flowableTaskService.getTodoTaskList(taskQueryVO, pageNum, pageSize);
        return ResponseResult.SUCCESS(pager);
    }

    @Override
    public ResponseResult<Pager<TaskVO>> doneTasklistPage(TaskQueryVO taskQueryVO, Integer pageNum, Integer pageSize) {
        Pager<TaskVO> pager = flowableTaskService.getDoneTaskList(taskQueryVO, pageNum, pageSize);
        return ResponseResult.SUCCESS(pager);
    }

    @Override
    public ResponseResult<Pager<ProcessInstanceVO>> sentTasklistPage(ProcessInstanceQueryVO processInstanceQueryVO, Integer pageNum, Integer pageSize) {
        Pager<ProcessInstanceVO> pager = flowableTaskService.getSentTaskList(processInstanceQueryVO, pageNum, pageSize);
        return ResponseResult.SUCCESS(pager);
    }

    @Override
    public ResponseResult approveTask(ApproveTaskVO approveTaskVO) {
        flowableTaskService.complete(approveTaskVO);
        return ResponseResult.SUCCESS();
    }

    @Override
    public ResponseResult withdrawTask(WithdrawTaskVO withdrawTaskVO) {
        flowableTaskService.withdraw(withdrawTaskVO);
        return ResponseResult.SUCCESS();
    }

}
