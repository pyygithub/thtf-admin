package com.thtf.flowable.controller;

import com.thtf.common.core.response.Pager;
import com.thtf.common.core.response.ResponseResult;
import com.thtf.flowable.api.FlowableProcessDefinitionControllerApi;
import com.thtf.flowable.entity.FlowProcessDefinition;
import com.thtf.flowable.service.FlowableProcessDefinitionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

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

    @Override
    public void loadXmlByProcessDefinitionId(String id, HttpServletResponse response) {
        try {
            byte[] flowXml = flowableProcessDefinitionService.getProcessDefinitionXMLByModelId(id);
            response.setHeader("Content-type", "text/xml;charset=UTF-8");
            response.getOutputStream().write(flowXml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadPngByByProcessDefinitionId(String id, HttpServletResponse response) {
        InputStream flowPng = flowableProcessDefinitionService.getProcessDefinitionPngByModelId(id);
        try {
            response.setHeader("Content-Type", "image/png");
            byte[] b = new byte[1024];
            int len;
            while ((len = flowPng.read(b, 0, 1024)) != -1) {
                response.getOutputStream().write(b, 0, len);
            }
            log.info("下载完毕");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResponseResult deleteByProcessDefinitionId(String id) {
        flowableProcessDefinitionService.deleteById(id);
        return ResponseResult.SUCCESS();
    }
}
