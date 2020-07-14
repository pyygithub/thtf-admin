package com.thtf.flowable.api;

import com.thtf.common.core.response.ResponseResult;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * ---------------------------
 * 模板API
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020-07-14 13:09:13
 * 版本：  v1.0
 * ---------------------------
 */
@Api(tags="模板API")
public interface FlowableManagerControllerApi {

    String PATH_PREFIX = "/manager";

    /**
     * 检查流程模板文件格式
     *
     * @param file 流程文件
     */
    @PostMapping(value = PATH_PREFIX + "/check-upload", consumes = "multipart/*", headers = "content-type=multipart/form-data")
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "检查流程模板文件格式", notes = "传入文件")
    ResponseResult checkUpload(@RequestParam MultipartFile file);

    /**
     * 上传部署流程文件
     *
     * @param file      流程文件
     * @param tenantIds 租户
     * @param userId    当前用户ID
     */
    @PostMapping(value = PATH_PREFIX + "/upload-process-model", consumes = "multipart/*", headers = "content-type=multipart/form-data")
    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "上传流程模板文件", notes = "传入文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tenantIds", value = "租户ID,逗号分隔", required = false, dataType = "String", paramType = "form"),
            @ApiImplicitParam(name = "userId", value = "当前用户ID", required = false, dataType = "String", paramType = "form")
    })
    ResponseResult uploadProcessModelFile(@RequestParam MultipartFile file,
                                          @RequestParam(required = false, defaultValue = "") String tenantIds,
                                          @RequestHeader(required = false, defaultValue = "") String userId);
}
