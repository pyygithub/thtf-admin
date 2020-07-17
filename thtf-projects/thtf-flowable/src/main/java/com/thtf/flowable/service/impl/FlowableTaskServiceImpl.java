package com.thtf.flowable.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.thtf.common.core.exception.BusinessException;
import com.thtf.common.core.response.Pager;
import com.thtf.flowable.constants.FlowableConstant;
import com.thtf.flowable.enums.CommentTypeEnum;
import com.thtf.flowable.enums.FlowableCode;
import com.thtf.flowable.mapper.FlowableTaskMapper;
import com.thtf.flowable.service.FlowableBpmnModelService;
import com.thtf.flowable.service.FlowableProcessInstanceService;
import com.thtf.flowable.service.FlowableTaskService;
import com.thtf.flowable.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.flowable.bpmn.model.Activity;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.service.impl.persistence.entity.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class FlowableTaskServiceImpl extends BaseProcessService implements FlowableTaskService {

    @Autowired
    private FlowableTaskMapper flowableTaskMapper;

    @Autowired
    private FlowableProcessInstanceService flowableProcessInstanceService;

    @Autowired
    private FlowableBpmnModelService flowableBpmnModelService;

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

    @Override
    public void complete(ApproveTaskVO approveTaskVO) {
        // 查看当前任务是存在
        TaskEntity taskEntity = (TaskEntity) taskService.createTaskQuery().taskId(approveTaskVO.getTaskId()).singleResult();
        if (taskEntity == null) {
            throw new BusinessException(FlowableCode.FLOW_TASK_NO_FOUNED);
        }
        // 查看当前任务是否被挂起
        if (taskEntity.isSuspended()) {
            throw new BusinessException(FlowableCode.FLOW_SUSPENDED);
        }
        // 完成任务
        taskService.complete(approveTaskVO.getTaskId(), approveTaskVO.getVariables());

        // 添加审批意见
        this.addComment(approveTaskVO.getTaskId(),
                approveTaskVO.getUserCode(),
                approveTaskVO.getProcessInstanceId(),
                CommentTypeEnum.SP.toString(),
                approveTaskVO.getComment());

    }

    @Override
    public void withdraw(WithdrawTaskVO withdrawTaskVO) {
//        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
//                .processInstanceId(withdrawTaskVO.getProcessInstanceId()).singleResult();
//        if (processInstance == null) {
//            throw new BusinessException(FlowableCode.FLOW_INSTANCE_NO_FOUNED);
//        }
//        //1.添加撤回意见
//        this.addComment(withdrawTaskVO.getUserCode(), withdrawTaskVO.getProcessInstanceId(), CommentTypeEnum.CH.toString(), revokeVo.getMessage());
//        //2.设置提交人
//        runtimeService.setVariable(withdrawTaskVO.getProcessInstanceId(), FlowableConstant.FLOW_SUBMITTER_VAR, processInstance.getStartUserId());
//        //3.执行撤回
//        Activity disActivity = flowableBpmnModelService.findActivityByName(processInstance.getProcessDefinitionId(), FlowableConstant.FLOW_SUBMITTER);
//        //4.删除运行和历史的节点信息
//        this.deleteActivity(disActivity.getId(), revokeVo.getProcessInstanceId());
//        //5.执行跳转
//        List<Execution> executions = runtimeService.createExecutionQuery().parentId(revokeVo.getProcessInstanceId()).list();
//        List<String> executionIds = new ArrayList<>();
//        executions.forEach(execution -> executionIds.add(execution.getId()));
//        this.moveExecutionsToSingleActivityId(executionIds, disActivity.getId());
//        returnVo = new ReturnVo<>(ReturnCode.SUCCESS, "撤回成功!");
//
//        return returnVo;
    }

}
