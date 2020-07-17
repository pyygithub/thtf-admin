package com.thtf.flowable.controller;

import com.thtf.common.core.response.Pager;
import com.thtf.common.core.response.ResponseResult;
import com.thtf.flowable.api.FlowableProcessInstanceControllerApi;
import com.thtf.flowable.cmd.DeleteFlowableProcessInstanceCmd;
import com.thtf.flowable.constants.FlowableConstant;
import com.thtf.flowable.service.FlowableProcessInstanceService;
import com.thtf.flowable.vo.EndProcessInstanceVO;
import com.thtf.flowable.vo.ProcessInstanceQueryVO;
import com.thtf.flowable.vo.ProcessInstanceVO;
import com.thtf.flowable.vo.StartProcessInstanceVO;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.HistoryService;
import org.flowable.engine.ManagementService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

@Slf4j
@RestController
public class FlowableProcessInstanceController implements FlowableProcessInstanceControllerApi {

    @Autowired
    private FlowableProcessInstanceService flowableProcessInstanceService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private ManagementService managementService;

    @Override
    public ResponseResult startProcessInstance(StartProcessInstanceVO startProcessInstanceVO) {
        String processInstanceId = flowableProcessInstanceService.startProcess(startProcessInstanceVO);
        return ResponseResult.SUCCESS(processInstanceId);
    }

    @Override
    public ResponseResult endProcessInstance(EndProcessInstanceVO endProcessInstanceVO) {
        String processInstanceId = flowableProcessInstanceService.endProcess(endProcessInstanceVO);
        return ResponseResult.SUCCESS(processInstanceId);
    }

    @Override
    public ResponseResult<Pager<ProcessInstanceVO>> listPage(ProcessInstanceQueryVO processInstanceQueryVO, Integer pageNum, Integer pageSize) {
        Pager<ProcessInstanceVO> pager = flowableProcessInstanceService.getList(processInstanceQueryVO, pageNum, pageSize);
        return ResponseResult.SUCCESS(pager);
    }

    @Override
    public void processInstanceImageTrack(String processInstanceId, HttpServletResponse response) {
        try {
            byte[] b = flowableProcessInstanceService.processInstanceImageTrack(processInstanceId);
            response.setHeader("Content-Type", "image/png");
            response.getOutputStream().write(b);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResponseResult suspendOrActivateByProcessInstanceId(String processInstanceId, Integer status) {
        if (FlowableConstant.SUSPEND == status) {
            runtimeService.suspendProcessInstanceById(processInstanceId);
            log.info("流程实例 processInstanceId = {}, 挂起成功", processInstanceId);
        } else {
            runtimeService.activateProcessInstanceById(processInstanceId);
            log.info("流程实例 processInstanceId = {}, 激活成功", processInstanceId);
        }
        return ResponseResult.SUCCESS();
    }

    @Override
    public ResponseResult deleteByProcessInstanceId(String processInstanceId) {
        long count = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).count();
        // 删除运行时流程实例
        if (count > 0) {
            DeleteFlowableProcessInstanceCmd cmd = new DeleteFlowableProcessInstanceCmd(processInstanceId, "删除流程实例", true);
            managementService.executeCommand(cmd);
            log.info("processInstanceId = {} 删除运行时流程实例成功", processInstanceId);
        }
        // 删除历史历程实例
        else {
            historyService.deleteHistoricProcessInstance(processInstanceId);
            log.info("processInstanceId = {} 删除历史历程实例成功", processInstanceId);
        }
        return ResponseResult.SUCCESS();
    }
}
