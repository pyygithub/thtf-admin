package com.thtf.flowable.mapper;

import java.util.List;

public interface FlowableRunActinstMapper {

    /**
     * 删除节点信息
     *
     * @param ids ids
     */
    void deleteRunActinstsByIds(List<String> ids) ;
}
