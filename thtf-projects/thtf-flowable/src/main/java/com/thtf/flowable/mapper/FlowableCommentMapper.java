package com.thtf.flowable.mapper;


import com.thtf.flowable.vo.CommentVO;

import java.util.List;

public interface FlowableCommentMapper {

    /**
     * 通过流程实例id获取审批意见列表
     *
     * @param ProcessInstanceId 流程实例id
     * @return
     */
    List<CommentVO> getFlowCommentVosByProcessInstanceId(String ProcessInstanceId);

}
