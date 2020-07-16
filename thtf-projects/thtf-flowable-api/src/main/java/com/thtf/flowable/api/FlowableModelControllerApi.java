package com.thtf.flowable.api;

import com.thtf.common.core.response.Pager;
import com.thtf.common.core.response.ResponseResult;
import com.thtf.flowable.entity.FlowableModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * ---------------------------
 * 模板API
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020-07-14 13:09:13
 * 版本：  v1.0
 * ---------------------------
 */
@Api(tags="流程模板API")
public interface FlowableModelControllerApi {

    String PATH_PREFIX = "/model";

    /**
     * 检查流程模板文件格式
     *
     * @param file 流程文件
     */
    @PostMapping(value = PATH_PREFIX + "/check-upload", consumes = "multipart/*", headers = "content-type=multipart/form-data")
    @ApiOperation(value = "检查流程模板文件格式", notes = "传入文件")
    ResponseResult checkUpload(@RequestParam MultipartFile file);

    /**
     * 上传部署流程文件
     *
     * @param file      流程文件
     * @param userId    当前用户ID
     */
    @PostMapping(value = PATH_PREFIX + "/upload", consumes = "multipart/*", headers = "content-type=multipart/form-data")
    @ApiOperation(value = "上传流程模板文件", notes = "传入文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "当前用户ID", required = false,  dataType = "String", paramType = "header")
    })
    ResponseResult uploadProcessModelFile(@RequestParam MultipartFile file,@RequestHeader(required = false, defaultValue = "") String userId);

    /**
     * 流程模板分页模糊列表
     *
     * @param modelKey  模型标识
     * @param name      模型名称
     * @param pageNum   当前页码
     * @param pageSize  分页尺寸
     * @return
     */
    @GetMapping(PATH_PREFIX + "/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "modelKey", value = "模型标识", required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "name", value = "模型名称",  required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "pageNum", value = "当前页码",  required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "分页尺寸",  required = true, dataType = "int", paramType = "query"),
    })
    @ApiOperation(value = "流程模板列表查询", notes = "流程模板分页模糊列表")
    ResponseResult<Pager<FlowableModel>> list(@RequestParam(required = false, defaultValue = "") String modelKey,
                                              @RequestParam(required = false, defaultValue = "") String name,
                                              @RequestParam(required = true, defaultValue = "1") Integer pageNum,
                                              @RequestParam(required = true, defaultValue = "10") Integer pageSize);

    /**
     * 模板部署
     *
     * @param modelId   模板ID
     * @param category  分类
     * @param tenantIds 租户ID集合, 逗号分隔
     * @return
     */
    @PostMapping(PATH_PREFIX + "/deployment")
    @ApiOperation(value = "检查流程模板文件格式", notes = "传入文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "modelId", value = "模板ID", required = true, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "category", value = "分类",  required = false, dataType = "string", paramType = "query"),
            @ApiImplicitParam(name = "tenantIds", value = "租户ID集合, 逗号分隔",  required = false, dataType = "string", paramType = "query"),
    })
    ResponseResult deploy(@RequestParam String modelId,
                          @RequestParam(required = false, defaultValue = "")  String category,
                          @RequestParam(required = false, defaultValue = "") String tenantIds);


    /**
     * 获取流程模板XML
     *
     * @param modelId 模板ID
     * @return
     */
    @GetMapping(PATH_PREFIX + "/loadXml/{modelId}")
    @ApiOperation(value = "获取流程模板XML", notes = "获取流程模板XML")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "modelId", value = "模板ID", required = true, dataType = "string", paramType = "path"),
    })
    void loadXmlByModelId(@PathVariable String modelId, HttpServletResponse response);

    /**
     * 获取流程模板图片（png）
     *
     * @param modelId 模板ID
     * @return
     */
    @GetMapping(PATH_PREFIX + "/loadPng/{modelId}")
    @ApiOperation(value = "获取流程模板图片", notes = "获取流程模板图片（png）")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "modelId", value = "模板ID", required = true, dataType = "string", paramType = "path"),
    })
    void loadPngByModelId(@PathVariable String modelId, HttpServletResponse response);

    /**
     * 模板部署删除
     *
     * @param modelId   模板ID
     * @return
     */
    @DeleteMapping(PATH_PREFIX + "/{modelId}")
    @ApiOperation(value = "检查流程模板文件格式", notes = "传入文件")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "modelId", value = "模板ID", required = true, dataType = "string", paramType = "path"),
    })
    ResponseResult deleteByModelId(@PathVariable String modelId);
}
