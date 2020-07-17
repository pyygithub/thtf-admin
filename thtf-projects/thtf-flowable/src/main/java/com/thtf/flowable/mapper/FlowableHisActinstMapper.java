package com.thtf.flowable.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
public interface FlowableHisActinstMapper {

    /**
     * 删除节点信息
     *
     * @param ids ids
     */
    void deleteHisActinstsByIds(List<String> ids) ;
}
