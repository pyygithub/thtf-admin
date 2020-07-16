package com.thtf.flowable.controller;

import com.thtf.common.core.response.Pager;
import com.thtf.common.core.response.ResponseResult;
import com.thtf.flowable.api.FlowableProcessInstanceControllerApi;
import com.thtf.flowable.service.FlowableProcessInstanceService;
import com.thtf.flowable.vo.ProcessInstanceQueryVO;
import com.thtf.flowable.vo.ProcessInstanceVO;
import com.thtf.flowable.vo.StartProcessInstanceVO;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class FlowableProcessInstanceController implements FlowableProcessInstanceControllerApi {

    @Autowired
    private FlowableProcessInstanceService flowableProcessInstanceService;

    @Override
    public ResponseResult startProcess(StartProcessInstanceVO startProcessInstanceVO) {
        String processInstanceId = flowableProcessInstanceService.startProcess(startProcessInstanceVO);
        return ResponseResult.SUCCESS(processInstanceId);
    }

    @Override
    public ResponseResult<Pager<ProcessInstanceVO>> listPage(ProcessInstanceQueryVO processInstanceQueryVO, Integer pageNum, Integer pageSize) {
        Pager<ProcessInstanceVO> pager = flowableProcessInstanceService.getList(processInstanceQueryVO, pageNum, pageSize);
        return ResponseResult.SUCCESS(pager);
    }
}
