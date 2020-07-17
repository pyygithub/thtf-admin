package com.thtf.flowable.service;


import com.thtf.flowable.vo.CommentVO;

import java.util.List;

public interface FlowableCommentService {

    /**
     * 添加备注
     * @param comment 参数
     */
    void addComment(CommentVO comment) ;

    /**
     * 通过流程实例id获取审批意见列表
     * @param processInstanceId 流程实例id
     * @return
     */
    List<CommentVO> getFlowCommentVosByProcessInstanceId(String processInstanceId) ;

}
