package com.thtf.flowable.service;

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
}
