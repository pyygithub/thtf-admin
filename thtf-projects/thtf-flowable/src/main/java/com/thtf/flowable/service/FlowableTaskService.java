package com.thtf.flowable.service;

import com.thtf.common.core.response.Pager;
import com.thtf.flowable.vo.*;

public interface FlowableTaskService {
    Pager<TaskVO> getTodoTaskList(TaskQueryVO taskQueryVO, Integer pageNum, Integer pageSize);

    Pager<TaskVO> getDoneTaskList(TaskQueryVO taskQueryVO, Integer pageNum, Integer pageSize);

    Pager<ProcessInstanceVO> getSentTaskList(ProcessInstanceQueryVO processInstanceQueryVO, Integer pageNum, Integer pageSize);

    void complete(ApproveTaskVO approveTaskVO);

    void withdraw(WithdrawTaskVO withdrawTaskVO);
}
