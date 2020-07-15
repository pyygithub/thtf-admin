package com.thtf.flowable.service;

import com.thtf.common.core.response.Pager;
import com.thtf.flowable.entity.FlowProcessDefinition;

import java.io.InputStream;

public interface FlowableProcessDefinitionService {

    Pager<FlowProcessDefinition> listPage(String tenantId, String key, String name, String category, Integer pageNum, Integer pageSize);

    byte[] getProcessDefinitionXMLByModelId(String id);

    InputStream getProcessDefinitionPngByModelId(String id);

    void deleteById(String id);
}
