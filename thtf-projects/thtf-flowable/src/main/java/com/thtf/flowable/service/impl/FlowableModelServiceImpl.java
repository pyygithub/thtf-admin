package com.thtf.flowable.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.thtf.common.core.exception.BusinessException;
import com.thtf.common.core.exception.ExceptionCast;
import com.thtf.common.core.response.Pager;
import com.thtf.flowable.constants.FlowableEngineConstant;
import com.thtf.flowable.entity.FlowModel;
import com.thtf.flowable.enums.FlowableEngineCode;
import com.thtf.flowable.mapper.FlowMapper;
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
public class FlowableModelServiceImpl extends ServiceImpl<FlowMapper, FlowModel> implements FlowableModelService {

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private ModelService modelService;

    @Autowired
    private FlowMapper flowMapper;

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
        if (fileName != null && (fileName.endsWith(FlowableEngineConstant.SUFFIX))) {
            try {
                XMLInputFactory xif = XmlUtil.createSafeXmlInputFactory();
                InputStreamReader xmlIn = new InputStreamReader(file.getInputStream(), "UTF-8");
                XMLStreamReader xtr = xif.createXMLStreamReader(xmlIn);
                BpmnModel bpmnModel = bpmnXmlConverter.convertToBpmnModel(xtr);
                if (CollUtil.isEmpty(bpmnModel.getProcesses())) {
                    ExceptionCast.cast(FlowableEngineCode.FLOW_FILE_NOT_FOUND);
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
                ExceptionCast.cast(FlowableEngineCode.FLOW_FILE_UPLOAD_FAIL);
            }
        } else {
            ExceptionCast.cast(FlowableEngineCode.FLOW_FILE_TYPE_INVALID);
        }
    }

    @Override
    public Pager<FlowModel> listPage(String modelKey, String name, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<FlowModel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .like(StrUtil.isNotBlank(modelKey), FlowModel::getModelKey, modelKey)
                .like(StrUtil.isNotBlank(name), FlowModel::getName, name)
                .orderByDesc(FlowModel::getCreated);

        Page<FlowModel> page = new Page(pageNum, pageSize);
        List<FlowModel> records = flowMapper.selectPage(page, queryWrapper).getRecords();

        Pager<FlowModel> modelPager = new Pager<>();
        modelPager.setTotal(page.getTotal());
        modelPager.setRecords(records);
        return modelPager;
    }

    @Override
    public void deployModel(String modelId, String category, String tenantIds) {
        FlowModel model = this.getById(modelId);
        if (model == null) {
            throw new BusinessException(FlowableEngineCode.FLOW_NO_FOUND_MODEL);
        }
        byte[] bytes = getBpmnXML(model);
        String processName = model.getName();
        if (!StrUtil.endWithIgnoreCase(processName, FlowableEngineConstant.SUFFIX)) {
            processName += FlowableEngineConstant.SUFFIX;
        }
        String finalProcessName = processName;
        if (StrUtil.isNotEmpty(tenantIds)) {
            String[] tenantIdArr = tenantIds.split(",");
            for (String tenantId : tenantIdArr) {
                Deployment deployment = repositoryService.createDeployment().addBytes(finalProcessName, bytes).name(model.getName()).key(model.getModelKey()).tenantId(tenantId).deploy();
                deploy(deployment, category);
            }
        } else {
            Deployment deployment = repositoryService.createDeployment().addBytes(finalProcessName, bytes).name(model.getName()).key(model.getModelKey()).deploy();
            deploy(deployment, category);
        }
    }

    @Override
    public byte[] getModelXMLByModelId(String modelId) {
        FlowModel model = this.getById(modelId);
        if (model == null) {
            throw new BusinessException(FlowableEngineCode.FLOW_NO_FOUND_MODEL);
        }
        byte[] b = this.getBpmnXML(model);
        return b;
    }

    @Override
    public InputStream getModelPngByModelId(String modelId) {
        FlowModel flowModel = this.getById(modelId);
        FlowModel model = this.getById(modelId);
        if (model == null) {
            throw new BusinessException(FlowableEngineCode.FLOW_NO_FOUND_MODEL);
        }
        BpmnModel bpmnModel = this.getBpmnModel(flowModel);

        InputStream is = flowProcessDiagramGenerator.generateDiagram(bpmnModel);
        return is;
    }

    @Override
    public void deleteModel(String modelId) {
        int rowCount = flowMapper.deleteById(modelId);
        if (rowCount != 1) {
            throw new BusinessException(FlowableEngineCode.FLOW_DELETE_FAIL);
        }
    }

    private boolean deploy(Deployment deployment, String category) {
        log.debug("流程部署--------deploy:  " + deployment + "  分类---------->" + category);
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).list();
        StringBuilder logBuilder = new StringBuilder(500);
        List<Object> logArgs = new ArrayList<>();
        // 设置流程分类
        for (ProcessDefinition processDefinition : list) {
            if (StrUtil.isNotBlank(category)) {
                repositoryService.setProcessDefinitionCategory(processDefinition.getId(), category);
            }
            logBuilder.append("部署成功,流程ID={} \n");
            logArgs.add(processDefinition.getId());
        }
        if (list.size() == 0) {
            ExceptionCast.cast(FlowableEngineCode.FLOW_DEPLOY_FAIL);
            return false;
        } else {
            log.info(logBuilder.toString(), logArgs.toArray());
            return true;
        }
    }

    private byte[] getBpmnXML(FlowModel model) {
        BpmnModel bpmnModel = getBpmnModel(model);
        return getBpmnXML(bpmnModel);
    }

    private byte[] getBpmnXML(BpmnModel bpmnModel) {
        for (Process process : bpmnModel.getProcesses()) {
            if (StringUtils.isNotEmpty(process.getId())) {
                char firstCharacter = process.getId().charAt(0);
                if (Character.isDigit(firstCharacter)) {
                    process.setId("a" + process.getId());
                }
            }
        }
        return bpmnXmlConverter.convertToXML(bpmnModel);
    }

    private BpmnModel getBpmnModel(FlowModel model) {
        BpmnModel bpmnModel;
        try {
            Map<String, FlowModel> formMap = new HashMap<>(16);
            Map<String, FlowModel> decisionTableMap = new HashMap<>(16);

            List<FlowModel> referencedModels = baseMapper.findByParentModelId(model.getId());
            for (FlowModel childModel : referencedModels) {
                if (FlowModel.MODEL_TYPE_FORM == childModel.getModelType()) {
                    formMap.put(childModel.getId(), childModel);

                } else if (FlowModel.MODEL_TYPE_DECISION_TABLE == childModel.getModelType()) {
                    decisionTableMap.put(childModel.getId(), childModel);
                }
            }
            bpmnModel = getBpmnModel(model, formMap, decisionTableMap);
            return bpmnModel;
        } catch (Exception e) {
            log.error("Could not generate BPMN 2.0 model for {}", model.getId(), e);
            throw new BusinessException(FlowableEngineCode.FLOW_GENERATE_BPMN_FAIL);
        }
    }

    private BpmnModel getBpmnModel(FlowModel model, Map<String, FlowModel> formMap, Map<String, FlowModel> decisionTableMap) {
        try {
            ObjectNode editorJsonNode = (ObjectNode) objectMapper.readTree(model.getModelEditorJson());
            Map<String, String> formKeyMap = new HashMap<>(16);
            for (FlowModel formModel : formMap.values()) {
                formKeyMap.put(formModel.getId(), formModel.getModelKey());
            }
            Map<String, String> decisionTableKeyMap = new HashMap<>(16);
            for (FlowModel decisionTableModel : decisionTableMap.values()) {
                decisionTableKeyMap.put(decisionTableModel.getId(), decisionTableModel.getModelKey());
            }
            return bpmnJsonConverter.convertToBpmnModel(editorJsonNode, formKeyMap, decisionTableKeyMap);
        } catch (Exception e) {
            log.error("Could not generate BPMN 2.0 model for {}", model.getId(), e);
            throw new BusinessException(FlowableEngineCode.FLOW_GENERATE_BPMN_FAIL);
        }
    }
}