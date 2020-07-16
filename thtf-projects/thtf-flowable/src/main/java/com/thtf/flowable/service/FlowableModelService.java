package com.thtf.flowable.service;

import com.thtf.common.core.response.Pager;
import com.thtf.flowable.entity.FlowableModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface FlowableModelService {

    /**
     * 上传流程模板文件
     *
     * @param file         流程配置文件
     * @param userId       当前用户ID
     * @return
     */
    void uploadProcessModelFile(MultipartFile file, String userId);

    /**
     * 流程模板分页模糊列表
     *
     * @param modelKey  模型标识
     * @param name      模型名称
     * @param pageNum   当前页码
     * @param pageSize  分页尺寸
     * @return
     */
    Pager<FlowableModel> listPage(String modelKey, String name, Integer pageNum, Integer pageSize);

    /**
     * 模板部署
     *
     * @param modelId   模板ID
     * @param category  分类
     * @param tenantIds 租户ID集合,逗号分隔
     */
    void deployModel(String modelId, String category, String tenantIds);

    /**
     * 获取流程模板XML
     *
     * @param modelId
     * @return
     */
    byte[] getModelXMLByModelId(String modelId);

    /**
     * 获取流程模板文件InputStream
     *
     * @param modelId
     * @return
     */
    InputStream getModelPngByModelId(String modelId);

    /**
     * 流程模板删除
     *
     * @param modelId 模板ID
     */
    void deleteModel(String modelId);
}
