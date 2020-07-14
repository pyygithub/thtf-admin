package com.thtf.flowable.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.func.Func;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.thtf.common.core.exception.ExceptionCast;
import com.thtf.common.core.response.Pager;
import com.thtf.flowable.constants.FlowableEngineConstant;
import com.thtf.flowable.entity.FlowModel;
import com.thtf.flowable.enums.FlowableEngineCode;
import com.thtf.flowable.mapper.FlowMapper;
import com.thtf.flowable.service.FlowableManagerService;
import com.thtf.flowable.utils.UserUtil;
import lombok.extern.slf4j.Slf4j;
import org.flowable.bpmn.BpmnAutoLayout;
import org.flowable.bpmn.converter.BpmnXMLConverter;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.editor.language.json.converter.BpmnJsonConverter;
import org.flowable.editor.language.json.converter.util.CollectionUtils;
import org.flowable.idm.api.User;
import org.flowable.ui.common.model.RemoteUser;
import org.flowable.ui.common.util.XmlUtil;
import org.flowable.ui.modeler.domain.AbstractModel;
import org.flowable.ui.modeler.domain.Model;
import org.flowable.ui.modeler.model.ModelRepresentation;
import org.flowable.ui.modeler.repository.ModelRepository;
import org.flowable.ui.modeler.serviceapi.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStreamReader;
import java.util.Calendar;
import java.util.List;

@Slf4j
@Service
public class FlowableManagerServiceImpl implements FlowableManagerService {

    @Autowired
    private ModelRepository modelRepository;

    @Autowired
    private ModelService modelService;

    @Autowired
    private FlowMapper flowMapper;

    protected BpmnXMLConverter bpmnXmlConverter = new BpmnXMLConverter();
    protected BpmnJsonConverter bpmnJsonConverter = new BpmnJsonConverter();

    @Override
    public void uploadProcessModelFile(MultipartFile file, String tenantIds, String userId) {
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
                for (String tenantId : tenantIds.split(",")) {
                    ModelRepresentation model = new ModelRepresentation();
                    model.setTenantId(tenantId);
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
                }

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
}
