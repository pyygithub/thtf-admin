package com.thtf.flowable.controller;

import cn.hutool.core.util.StrUtil;
import com.thtf.common.core.response.ResponseResult;
import com.thtf.flowable.api.FlowableManagerControllerApi;
import com.thtf.flowable.constants.FlowableEngineConstant;
import com.thtf.flowable.enums.FlowableEngineCode;
import com.thtf.flowable.service.FlowableManagerService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiOperationSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Slf4j
@RestController
public class FlowableManagerController implements FlowableManagerControllerApi {

    @Autowired
    private FlowableManagerService flowableManagerService;

    @Override
    public ResponseResult checkUpload(MultipartFile file) {
        boolean temp = Objects.requireNonNull(file.getOriginalFilename()).endsWith(FlowableEngineConstant.SUFFIX);
        if (!temp) {
            return new ResponseResult(FlowableEngineCode.FLOW_FILE_TYPE_INVALID);
        }
        return ResponseResult.SUCCESS();
    }

    @Override
    public ResponseResult uploadProcessModelFile(MultipartFile file, String tenantIds, String userId) {
        if (StrUtil.isBlank(tenantIds)) {
            tenantIds = "flow";
        }
        flowableManagerService.uploadProcessModelFile(file, tenantIds, userId);
        return ResponseResult.SUCCESS();
    }
}
