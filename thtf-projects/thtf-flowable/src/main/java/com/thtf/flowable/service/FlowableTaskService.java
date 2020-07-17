package com.thtf.flowable.service;

import com.thtf.common.core.response.Pager;
import com.thtf.flowable.vo.ProcessInstanceQueryVO;
import com.thtf.flowable.vo.ProcessInstanceVO;
import com.thtf.flowable.vo.TaskQueryVO;
import com.thtf.flowable.vo.TaskVO;

public interface FlowableTaskService {
    Pager<TaskVO> getTodoTaskList(TaskQueryVO taskQueryVO, Integer pageNum, Integer pageSize);

    Pager<TaskVO> getDoneTaskList(TaskQueryVO taskQueryVO, Integer pageNum, Integer pageSize);

    Pager<ProcessInstanceVO> getSentTaskList(ProcessInstanceQueryVO processInstanceQueryVO, Integer pageNum, Integer pageSize);
}
