package com.thtf.flowable.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.thtf.common.core.exception.BusinessException;
import com.thtf.common.core.response.Pager;
import com.thtf.flowable.cmd.AddHisCommentCmd;
import com.thtf.flowable.constants.FlowableConstant;
import com.thtf.flowable.enums.CommentTypeEnum;
import com.thtf.flowable.enums.FlowableCode;
import com.thtf.flowable.mapper.FlowableProcessInstanceMapper;
import com.thtf.flowable.service.FlowProcessDiagramGenerator;
import com.thtf.flowable.service.FlowableBpmnModelService;
import com.thtf.flowable.service.FlowableProcessInstanceService;
import com.thtf.flowable.vo.EndProcessInstanceVO;
import com.thtf.flowable.vo.ProcessInstanceQueryVO;
import com.thtf.flowable.vo.ProcessInstanceVO;
import com.thtf.flowable.vo.StartProcessInstanceVO;
import lombok.extern.slf4j.Slf4j;
import org.flowable.bpmn.constants.BpmnXMLConstants;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.EndEvent;
import org.flowable.bpmn.model.Process;
import org.flowable.common.engine.impl.util.IoUtil;
import org.flowable.engine.*;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class FlowableProcessInstanceServiceImpl extends BaseProcessService implements FlowableProcessInstanceService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ManagementService managementService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private FlowableProcessInstanceMapper flowableProcessInstanceMapper;

    @Autowired
    private FlowProcessDiagramGenerator flowProcessDiagramGenerator;

    @Autowired
    private FlowableBpmnModelService flowableBpmnModelService;

    @Override
    public String startProcess(StartProcessInstanceVO startProcessInstanceVO) {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionId(startProcessInstanceVO.getProcessDefinitionId())
                .latestVersion().singleResult();
        if (processDefinition == null) {
            throw new BusinessException(FlowableCode.FLOW_DEFINITION_NO_FOUNED);
        }
        if (processDefinition.isSuspended()) {
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
    public String endProcess(EndProcessInstanceVO endProcessInstanceVO) {
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId(endProcessInstanceVO.getProcessInstanceId()).singleResult();
        if (processInstance == null) {
            throw new BusinessException(FlowableCode.FLOW_INSTANCE_NO_FOUNED);
        }
        if (processInstance.isSuspended()){
            throw new BusinessException(FlowableCode.FLOW_SUSPENDED);
        }

        // 添加审批记录
        endProcessInstanceVO.setMessage("后台执行终止");
        this.addComment(endProcessInstanceVO.getUserCode(), endProcessInstanceVO.getProcessInstanceId(), CommentTypeEnum.LCZZ.toString(),
                endProcessInstanceVO.getMessage());
        // 获取流程结束节点
        List<EndEvent> endNodes = flowableBpmnModelService.findEndFlowElement(processInstance.getProcessDefinitionId());
        String endId = endNodes.get(0).getId();

        String processInstanceId = endProcessInstanceVO.getProcessInstanceId();
        List<Execution> executions = runtimeService.createExecutionQuery().parentId(processInstanceId).list();
        List<String> executionIds = executions.stream().map(Execution::getId).collect(Collectors.toList());
        // 执行终止 - 直接跳转到结束节点
        this.moveExecutionsToSingleActivityId(executionIds, endId);
        log.info("processInstanceId = {} 终止成功", processInstanceId);

        return processInstanceId;
    }

    @Override
    public Pager<ProcessInstanceVO> getList(ProcessInstanceQueryVO processInstanceQueryVO, Integer pageNum, Integer pageSize) {
        Page result = flowableProcessInstanceMapper.selectList(new Page(pageNum, pageSize), processInstanceQueryVO);

        Pager<ProcessInstanceVO> pager = new Pager<>();
        pager.setTotal(result.getTotal());
        pager.setRecords(result.getRecords());
        return pager;
    }

    @Override
    public byte[] processInstanceImageTrack(String processInstanceId) {
        //1. 获取当前的流程实例
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        String processDefinitionId = null;
        List<String> activeActivityIds = new ArrayList<>();
        List<String> highLightedFlows = new ArrayList<>();
        //2. 获取所有的历史轨迹线对象
        List<HistoricActivityInstance> historicSquenceFlows = historyService.createHistoricActivityInstanceQuery()
                .processInstanceId(processInstanceId).activityType(BpmnXMLConstants.ELEMENT_SEQUENCE_FLOW).list();
        historicSquenceFlows.forEach(historicActivityInstance -> highLightedFlows.add(historicActivityInstance.getActivityId()));
        //3. 获取流程定义id和高亮的节点id
        if (processInstance != null) {
            //3.1. 正在运行的流程实例
            processDefinitionId = processInstance.getProcessDefinitionId();
            activeActivityIds = runtimeService.getActiveActivityIds(processInstanceId);
        } else {
            //3.2. 已经结束的流程实例
            HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
            processDefinitionId = historicProcessInstance.getProcessDefinitionId();
            //3.3. 获取结束节点列表
            List<HistoricActivityInstance> historicEnds = historyService.createHistoricActivityInstanceQuery()
                    .processInstanceId(processInstanceId).activityType(BpmnXMLConstants.ELEMENT_EVENT_END).list();
            List<String> finalActiveActivityIds = activeActivityIds;
            historicEnds.forEach(historicActivityInstance -> finalActiveActivityIds.add(historicActivityInstance.getActivityId()));
        }
        //4. 获取bpmnModel对象
        BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
        //5. 生成图片流
        InputStream inputStream = flowProcessDiagramGenerator.generateDiagram(bpmnModel, activeActivityIds, highLightedFlows);
        //6. 转化成byte便于网络传输
        byte[] datas = IoUtil.readInputStream(inputStream, "image inputStream name");
        return datas;
    }

}
