package com.thtf.flowable.service;

import com.thtf.common.core.response.Pager;
import com.thtf.flowable.entity.FlowProcessDefinition;

public interface FlowableProcessDefinitionService {

    Pager<FlowProcessDefinition> listPage(String tenantId, String key, String name, String category, Integer pageNum, Integer pageSize);
}
