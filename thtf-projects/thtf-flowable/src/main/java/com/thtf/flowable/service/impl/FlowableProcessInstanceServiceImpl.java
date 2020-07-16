package com.thtf.flowable.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.thtf.common.core.exception.BusinessException;
import com.thtf.common.core.response.Pager;
import com.thtf.flowable.cmd.AddHisCommentCmd;
import com.thtf.flowable.constants.FlowableConstant;
import com.thtf.flowable.enums.CommentTypeEnum;
import com.thtf.flowable.enums.FlowableCode;
import com.thtf.flowable.mapper.FlowableProcessInstanceMapper;
import com.thtf.flowable.service.FlowableProcessInstanceService;
import com.thtf.flowable.vo.ProcessInstanceQueryVO;
import com.thtf.flowable.vo.ProcessInstanceVO;
import com.thtf.flowable.vo.StartProcessInstanceVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.flowable.engine.IdentityService;
import org.flowable.engine.ManagementService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class FlowableProcessInstanceServiceImpl implements FlowableProcessInstanceService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ManagementService managementService;

    @Autowired
    private FlowableProcessInstanceMapper flowableProcessInstanceMapper;


    @Override
    public String startProcess(StartProcessInstanceVO startProcessInstanceVO) {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionId(startProcessInstanceVO.getProcessDefinitionId())
                .latestVersion().singleResult();
        if (processDefinition != null && processDefinition.isSuspended()) {
            throw new BusinessException(FlowableCode.FLOW_SUSPENDED);
        }

        /**
         * 1、设置变量
         * 1.1、设置提交人字段为空字符串让其自动跳过
         * 1.2、设置可以自动跳过
         * 1.3、汇报线的参数设置
         */

        //1.1 设置提交人字段为空字符串让其自动跳过
        startProcessInstanceVO.getVariables().put(FlowableConstant.FLOW_SUBMITTER_VAR, "");
        //1.2 设置可以自动跳过
        startProcessInstanceVO.getVariables().put(FlowableConstant.FLOWABLE_SKIP_EXPRESSION_ENABLED, true);
        // TODO 1.3汇报线的参数设置

        //2 启动流程
        identityService.setAuthenticatedUserId(startProcessInstanceVO.getCurrentUserCode());
        ProcessInstance processInstance = runtimeService.createProcessInstanceBuilder()
                .processDefinitionId(startProcessInstanceVO.getProcessDefinitionId().trim())
                .name(startProcessInstanceVO.getFormName().trim())
                .businessKey(startProcessInstanceVO.getBusinessKey().trim())
                .variables(startProcessInstanceVO.getVariables())
                .tenantId(startProcessInstanceVO.getTendantId().trim())
                .start();

        //3.添加审批记录
        managementService.executeCommand(new AddHisCommentCmd(null, startProcessInstanceVO.getCurrentUserCode(), processInstance.getProcessInstanceId(),
                CommentTypeEnum.TJ.toString(), startProcessInstanceVO.getFormName() + "提交"));

        //4.TODO 推送消息数据
        return processInstance.getId();
    }

    @Override
    public Pager<ProcessInstanceVO> getList(ProcessInstanceQueryVO processInstanceQueryVO, Integer pageNum, Integer pageSize) {
        Page result = flowableProcessInstanceMapper.selectList(new Page(pageNum, pageSize), processInstanceQueryVO);

        Pager<ProcessInstanceVO> pager = new Pager<>();
        pager.setTotal(result.getTotal());
        pager.setRecords(result.getRecords());
        return pager;
    }
}
