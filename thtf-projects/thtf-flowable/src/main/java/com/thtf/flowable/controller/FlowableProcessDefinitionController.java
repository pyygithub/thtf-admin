package com.thtf.flowable.controller;

import com.thtf.common.core.response.Pager;
import com.thtf.common.core.response.ResponseResult;
import com.thtf.flowable.api.FlowableProcessDefinitionControllerApi;
import com.thtf.flowable.entity.FlowProcessDefinition;
import com.thtf.flowable.service.FlowableProcessDefinitionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class FlowableProcessDefinitionController implements FlowableProcessDefinitionControllerApi {

    @Autowired
    private FlowableProcessDefinitionService flowableProcessDefinitionService;

    @Override
    public ResponseResult<Pager<FlowProcessDefinition>> list(String tenantId, String key, String name, String category,
                                                             Integer pageNum, Integer pageSize) {
        Pager<FlowProcessDefinition> processDefinitionPager = flowableProcessDefinitionService.listPage(tenantId, key, name, category,pageNum, pageSize);
        return ResponseResult.SUCCESS(processDefinitionPager);
    }
}
