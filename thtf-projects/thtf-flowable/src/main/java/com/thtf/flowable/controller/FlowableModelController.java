package com.thtf.flowable.controller;

import com.thtf.common.core.response.Pager;
import com.thtf.common.core.response.ResponseResult;
import com.thtf.flowable.api.FlowableModelControllerApi;
import com.thtf.flowable.constants.FlowableEngineConstant;
import com.thtf.flowable.entity.FlowModel;
import com.thtf.flowable.enums.FlowableEngineCode;
import com.thtf.flowable.service.FlowableModelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.Objects;

@Slf4j
@RestController
public class FlowableModelController implements FlowableModelControllerApi {

    @Autowired
    private FlowableModelService flowableModelService;

    @Override
    public ResponseResult checkUpload(MultipartFile file) {
        boolean temp = Objects.requireNonNull(file.getOriginalFilename()).endsWith(FlowableEngineConstant.SUFFIX);
        if (!temp) {
            return new ResponseResult(FlowableEngineCode.FLOW_FILE_TYPE_INVALID);
        }
        return ResponseResult.SUCCESS();
    }

    @Override
    public ResponseResult uploadProcessModelFile(MultipartFile file, String userId) {
        flowableModelService.uploadProcessModelFile(file, userId);
        return ResponseResult.SUCCESS();
    }

    @Override
    public ResponseResult<Pager<FlowModel>> list(String modelKey, String name, Integer pageNum, Integer pageSize) {
        Pager<FlowModel> modelPager = flowableModelService.listPage(modelKey, name, pageNum, pageSize);
        return ResponseResult.SUCCESS(modelPager);
    }

    @Override
    public ResponseResult deploy(String modelId, String category, String tenantIds) {
        flowableModelService.deployModel(modelId, category, tenantIds);
        return ResponseResult.SUCCESS();
    }

    @Override
    public void loadXmlByModelId(String modelId, HttpServletResponse response) {
        try {
            byte[] flowXml = flowableModelService.getModelXMLByModelId(modelId);
            response.setHeader("Content-type", "text/xml;charset=UTF-8");
            response.getOutputStream().write(flowXml);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadPngByModelId(String modelId, HttpServletResponse response) {
        InputStream flowInputStream = flowableModelService.getModelPngByModelId(modelId);
        try {
            response.setHeader("Content-Type", "image/png");
            byte[] b = new byte[1024];
            int len;
            while ((len = flowInputStream.read(b, 0, 1024)) != -1) {
                response.getOutputStream().write(b, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ResponseResult deleteByModelId(String modelId) {
        flowableModelService.deleteModel(modelId);
        return ResponseResult.SUCCESS();
    }


}
