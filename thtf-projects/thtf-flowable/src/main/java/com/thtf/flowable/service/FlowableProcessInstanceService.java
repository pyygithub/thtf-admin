package com.thtf.flowable.service;

import com.thtf.common.core.response.Pager;
import com.thtf.flowable.vo.EndProcessInstanceVO;
import com.thtf.flowable.vo.ProcessInstanceQueryVO;
import com.thtf.flowable.vo.ProcessInstanceVO;
import com.thtf.flowable.vo.StartProcessInstanceVO;

public interface FlowableProcessInstanceService {

    String startProcess(StartProcessInstanceVO startProcessInstanceVO);

    String endProcess(EndProcessInstanceVO endProcessInstanceVO);

    Pager<ProcessInstanceVO> getList(ProcessInstanceQueryVO processInstanceQueryVO, Integer pageNum, Integer pageSize);

    byte[] processInstanceImageTrack(String processInstanceId);

}
