package com.thtf.flowable.service.impl;


import com.thtf.flowable.cmd.AddFlowableHisCommentCmd;
import com.thtf.flowable.enums.CommentTypeEnum;
import com.thtf.flowable.mapper.FlowableCommentMapper;
import com.thtf.flowable.service.FlowableCommentService;
import com.thtf.flowable.vo.CommentVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlowableCommentServiceImpl extends BaseProcessService implements FlowableCommentService {

    @Autowired
    private FlowableCommentMapper flowableCommentMapper;

    @Override
    public void addComment(CommentVO comment) {
        managementService.executeCommand(new AddFlowableHisCommentCmd(comment.getTaskId(), comment.getUserId(), comment.getProcessInstanceId(),
                comment.getType(), comment.getMessage()));
    }

    @Override
    public List<CommentVO> getFlowCommentVosByProcessInstanceId(String processInstanceId) {
        List<CommentVO> commentVOList = flowableCommentMapper.getFlowCommentVosByProcessInstanceId(processInstanceId);
        commentVOList.forEach(commentVo -> {
            commentVo.setTypeName(CommentTypeEnum.getEnumMsgByType(commentVo.getType()));
        });
        return commentVOList;
    }
}
