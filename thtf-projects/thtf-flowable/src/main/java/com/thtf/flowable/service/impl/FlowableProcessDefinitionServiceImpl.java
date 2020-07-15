package com.thtf.flowable.service.impl;

import cn.hutool.core.util.StrUtil;
import com.thtf.common.core.response.Pager;
import com.thtf.flowable.entity.FlowProcessDefinition;
import com.thtf.flowable.service.FlowableProcessDefinitionService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.impl.persistence.entity.ProcessDefinitionEntityImpl;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.repository.ProcessDefinitionQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class FlowableProcessDefinitionServiceImpl implements FlowableProcessDefinitionService {

    @Autowired
    private RepositoryService repositoryService;

    @Override
    public Pager<FlowProcessDefinition> listPage(String tenantId, String key, String name, String category, Integer pageNum, Integer pageSize) {
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery()
                .latestVersion().orderByProcessDefinitionKey().asc();
        if (StrUtil.isBlank(tenantId)) {
            processDefinitionQuery.processDefinitionWithoutTenantId();
        }
        else {
            processDefinitionQuery.processDefinitionTenantId(tenantId);
        }
        if (StrUtil.isNotBlank(category)) {
            processDefinitionQuery.processDefinitionCategory(category);
        }
        if (StrUtil.isNotBlank(key)) {
            processDefinitionQuery.processDefinitionKeyLike(key);
        }
        if (StrUtil.isNotBlank(name)) {
            processDefinitionQuery.processDefinitionNameLike(name);
        }

        List<ProcessDefinition> processDefinitionList = processDefinitionQuery.listPage((pageNum - 1) * pageSize, pageSize);
        List<FlowProcessDefinition> flowProcessDefinitionList = new ArrayList<>();
        processDefinitionList.forEach(processDefinition -> {
            String deploymentId = processDefinition.getDeploymentId();
            Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult();
            FlowProcessDefinition flowProcess = new FlowProcessDefinition((ProcessDefinitionEntityImpl) processDefinition);
            flowProcess.setDeploymentTime(deployment.getDeploymentTime());
            flowProcessDefinitionList.add(flowProcess);
        });

        Pager<FlowProcessDefinition> processDefinitionPager = new Pager<>();
        processDefinitionPager.setTotal(processDefinitionQuery.count());
        processDefinitionPager.setRecords(flowProcessDefinitionList);
        return processDefinitionPager;
    }
}
