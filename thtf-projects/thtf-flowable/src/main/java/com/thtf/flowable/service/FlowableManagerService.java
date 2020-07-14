package com.thtf.flowable.service;

import com.thtf.common.core.response.Pager;
import com.thtf.flowable.entity.FlowModel;
import org.springframework.web.multipart.MultipartFile;

public interface FlowableManagerService {

    /**
     * 上传流程模板文件
     *
     * @param file         流程配置文件
     * @param tenantIds    租户id集合,逗号分隔
     * @param userId       当前用户ID
     * @return
     */
    void uploadProcessModelFile(MultipartFile file, String tenantIds, String userId);

    /**
     * 流程模板分页模糊列表
     *
     * @param modelKey  模型标识
     * @param name      模型名称
     * @param pageNum   当前页码
     * @param pageSize  分页尺寸
     * @return
     */
    Pager<FlowModel> listPage(String modelKey, String name, Integer pageNum, Integer pageSize);
}
