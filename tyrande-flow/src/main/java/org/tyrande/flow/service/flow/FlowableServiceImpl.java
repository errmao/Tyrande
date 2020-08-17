package org.tyrande.flow.service.flow;

import org.flowable.engine.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 工作流通用服务接口
 *
 * @author Tyrande
 */
@Service
public class FlowableServiceImpl implements FlowableService {

    /**
     * 业务流程的定义相关服务
     */
    @Resource
    private RepositoryService repositoryService;

    /**
     * 用户以及组管理相关服务
     */
    @Resource
    private IdentityService identityService;

    /**
     * 流程历史信息相关服务
     */
    @Resource
    private HistoryService historyService;

    /**
     * 流程对象实例相关服务
     */
    @Resource
    private RuntimeService runtimeService;

    /**
     * 流程任务节点相关服务
     */
    @Resource
    private TaskService taskService;

    /**
     * 管理和维护相关服务
     */
    @Resource
    private ManagementService managementService;

    /**
     * 表单引擎相关服务
     */
    @Resource
    private FormService formService;

    /**
     * 动态流程服务
     */
    @Resource
    private DynamicBpmnService dynamicBpmnService;
}
