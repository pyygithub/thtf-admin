package com.thtf.flowable.constants;

public interface FlowableConstant {
    String SUFFIX = ".bpmn20.xml";

    /**
     * 激活状态码
     */
    Integer ACTIVE = 1;

    /**
     * 挂起状态码
     */
    Integer SUSPEND = 2;

    /**
     * 提交人的变量名称
     */
    String FLOW_SUBMITTER_VAR = "initiator";

    /**
     * 自动跳过节点设置属性
     */
    String FLOWABLE_SKIP_EXPRESSION_ENABLED = "FLOWABLE_SKIP";

}
