package com.thtf.flowable.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.thtf.common.core.exception.BusinessException;
import com.thtf.common.core.exception.ExceptionCast;
import com.thtf.common.core.response.Pager;
import com.thtf.flowable.constants.FlowableConstant;
import com.thtf.flowable.entity.FlowableModel;
import com.thtf.flowable.enums.FlowableCode;
import com.thtf.flowable.mapper.FlowableModelMapper;
import com.thtf.flowable.service.FlowProcessDiagramGenerator;
import com.thtf.flowable.service.FlowableModelService;
import com.thtf.flowable.utils.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.flowable.bpmn.BpmnAutoLayout;
import org.flowable.bpmn.converter.BpmnXMLConverter;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.Process;
import org.flowable.editor.language.json.converter.BpmnJsonConverter;
import org.flowable.editor.language.json.converter.util.CollectionUtils;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.idm.api.User;
import org.flowable.ui.common.model.RemoteUser;
import org.flowable.ui.common.util.XmlUtil;
import org.flowable.ui.modeler.domain.AbstractModel;
import org.flowable.ui.modeler.domain.Model;
import org.flowable.ui.modeler.model.ModelRepresentation;
import org.flowable.ui.modeler.repository.ModelRepository;
import org.flowable.ui.modeler.serviceapi.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

@Slf4j
@Service
public class FlowableModelServiceImpl extends ServiceImpl<FlowableModelMapper, FlowableModel> implements FlowableModelService {

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private ModelService modelService;

    @Autowired
    private FlowableModelMapper flowableModelMapper;

    @Autowired
    private FlowProcessDiagramGenerator flowProcessDiagramGenerator;

    @Autowired
    private RepositoryService repositoryService;

    protected BpmnXMLConverter bpmnXmlConverter = new BpmnXMLConverter();

    protected BpmnJsonConverter bpmnJsonConverter = new BpmnJsonConverter();

    protected ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void uploadProcessModelFile(MultipartFile file, String userId) {
        String fileName = file.getOriginalFilename();
        if (fileName != null && (fileName.endsWith(FlowableConstant.SUFFIX))) {
            try {
                XMLInputFactory xif = XmlUtil.createSafeXmlInputFactory();
                InputStreamReader xmlIn = new InputStreamReader(file.getInputStream(), "UTF-8");
                XMLStreamReader xtr = xif.createXMLStreamReader(xmlIn);
                BpmnModel bpmnModel = bpmnXmlConverter.convertToBpmnModel(xtr);
                if (CollUtil.isEmpty(bpmnModel.getProcesses())) {
                    ExceptionCast.cast(FlowableCode.FLOW_FILE_NOT_FOUND);
                }
                if (0 == bpmnModel.getLocationMap().size()) {
                    BpmnAutoLayout bpmnLayout = new BpmnAutoLayout(bpmnModel);
                    bpmnLayout.execute();
                }
                ObjectNode modelNode = bpmnJsonConverter.convertToJson(bpmnModel);
                org.flowable.bpmn.model.Process process = bpmnModel.getMainProcess();
                String name = process.getId();
                if (StrUtil.isNotEmpty(process.getName())) {
                    name = process.getName();
                }
                String description = process.getDocumentation();
                ModelRepresentation model = new ModelRepresentation();
                model.setTenantId("flow_model");
                model.setKey(process.getId());
                model.setName(name);
                model.setDescription(description);
                model.setModelType(AbstractModel.MODEL_TYPE_BPMN);

                User createdBy = null;
                if (StrUtil.isNotBlank(userId)) {
                    createdBy = new RemoteUser();
                    createdBy.setId(userId);
                } else {
                    createdBy = UserUtil.getCurrentUser();
                }
                // 查询是否已经存在流程模板
                Model newModel = new Model();
                List<Model> models = modelRepository.findByKeyAndType(model.getKey(), model.getModelType());
                if (CollectionUtils.isNotEmpty(models)) {
                    Model updateModel = models.get(0);
                    newModel.setId(updateModel.getId());
                }
                newModel.setName(model.getName());
                newModel.setKey(model.getKey());
                newModel.setModelType(model.getModelType());
                newModel.setCreated(Calendar.getInstance().getTime());
                newModel.setCreatedBy(createdBy.getId());
                newModel.setDescription(model.getDescription());
                newModel.setModelEditorJson(modelNode.toString());
                newModel.setLastUpdated(Calendar.getInstance().getTime());
                newModel.setLastUpdatedBy(createdBy.getId());
                newModel.setTenantId(model.getTenantId());
                modelService.createModel(newModel, createdBy);
                log.info("Import success:{}", newModel.getName());

            }catch (Exception e) {
                log.error("Import failed for {}", fileName, e);
                ExceptionCast.cast(FlowableCode.FLOW_FILE_UPLOAD_FAIL);
            }
        } else {
            ExceptionCast.cast(FlowableCode.FLOW_FILE_TYPE_INVALID);
        }
    }

    @Override
    public Pager<FlowableModel> listPage(String modelKey, String name, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<FlowableModel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .like(StrUtil.isNotBlank(modelKey), FlowableModel::getModelKey, modelKey)
                .like(StrUtil.isNotBlank(name), FlowableModel::getName, name)
                .orderByDesc(FlowableModel::getCreated);

        IPage<FlowableModel> flowModelIPage = flowableModelMapper.selectPage(new Page(pageNum, pageSize), queryWrapper);

        Pager<FlowableModel> modelPager = new Pager<>();
        modelPager.setTotal(flowModelIPage.getTotal());
        modelPager.setRecords(flowModelIPage.getRecords());
        return modelPager;
    }

    @Override
    public void deployModel(String modelId, String category, String tenantIds) {
        Model model = modelService.getModel(modelId.trim());
        if (model == null) {
            throw new BusinessException(FlowableCode.FLOW_MODEL_NOT_FOUND);
        }
        BpmnModel bpmnModel = modelService.getBpmnModel(model);
        if (StrUtil.isNotBlank(tenantIds)) {
            String[] tenantIdArr = tenantIds.split(",");
            for (String tenantId : tenantIdArr) {
                deploy(category, model, bpmnModel, tenantId);
            }
        } else {
            //必须指定文件后缀名否则部署不成功
            deploy(category, model, bpmnModel, "");
        }
    }

    // 部署流程模板
    private void deploy(String category, Model model, BpmnModel bpmnModel, String tenantId) {
        //必须指定文件后缀名否则部署不成功
        Deployment deployment = repositoryService.createDeployment()
                .name(model.getName())
                .key(model.getKey())
                .category(category)
                .tenantId(tenantId)
                .addBpmnModel(model.getKey() + FlowableConstant.SUFFIX, bpmnModel)
                .deploy();
        // 根据部署ID获取对应的流程定义列表
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).list();
        // 设置流程分类
        for (ProcessDefinition processDefinition : list) {
            if (StrUtil.isNotBlank(category)) {
                repositoryService.setProcessDefinitionCategory(processDefinition.getId(), category);
            }
        }
    }

    @Override
    public byte[] getModelXMLByModelId(String modelId) {
        Model model = modelService.getModel(modelId);
        if (model == null) {
            throw new BusinessException(FlowableCode.FLOW_MODEL_NOT_FOUND);
        }
        byte[] b = modelService.getBpmnXML(model);
        return b;
    }

    @Override
    public InputStream getModelPngByModelId(String modelId) {
        Model model = modelService.getModel(modelId);
        if (model == null) {
            throw new BusinessException(FlowableCode.FLOW_MODEL_NOT_FOUND);
        }
        BpmnModel bpmnModel = modelService.getBpmnModel(model, new HashMap<>(), new HashMap<>());
        InputStream is = flowProcessDiagramGenerator.generateDiagram(bpmnModel);
        return is;
    }

    @Override
    public void deleteModel(String modelId) {
        int rowCount = flowableModelMapper.deleteById(modelId);
        if (rowCount != 1) {
            throw new BusinessException(FlowableCode.FLOW_DELETE_FAIL);
        }
    }


}
