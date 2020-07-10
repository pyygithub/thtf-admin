package com.thtf.base.server.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.thtf.base.api.entity.SysDept;
import com.thtf.base.api.vo.SysDeptSaveOrUpdateVO;
import com.thtf.base.api.vo.SysDeptTreeVO;
import com.thtf.base.server.constants.BaseServerCode;
import com.thtf.base.server.mapper.SysDeptMapper;
import com.thtf.base.server.service.SysDeptService;
import com.thtf.common.core.exception.ExceptionCast;
import com.thtf.common.core.response.CommonCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * ---------------------------
 * 部门 (DeptServiceImpl)         
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019-12-31 16:10:54
 * 版本：  v1.0
 * ---------------------------
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
public class SysDeptServiceImpl implements SysDeptService {

	@Autowired
	private SysDeptMapper sysDeptMapper;

    /**
     * 部门保存
     * @param sysDeptSaveOrUpdateVO
     */
	@Override
	public SysDeptTreeVO save(SysDeptSaveOrUpdateVO sysDeptSaveOrUpdateVO) {
        // 初始化Model对象
        SysDept sysDept = new SysDept();
        // 属性赋值
        BeanUtils.copyProperties(sysDeptSaveOrUpdateVO, sysDept);
        sysDept.setId(null); // 确保ID为null，默认使用mybatis-plus ID生成策略
        // 执行保存
        int row = sysDeptMapper.insert(sysDept);
        if (row != 1) {
            ExceptionCast.cast(BaseServerCode.SAVE_ERROR);
        }
        // 转换为VO对象
        SysDeptTreeVO sysDeptTreeVO = new SysDeptTreeVO();
        BeanUtils.copyProperties(sysDept, sysDeptTreeVO);
        log.info("### 部门保存完毕 ###");
        // 返回保存后结果
        return sysDeptTreeVO;
	}

    /**
     * 部门删除
     * @param id
     */
    @Override
    public void delete(String id) {
        // 参数校验
        if (StringUtils.isBlank(id)) {
           ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        // 根据ID查询部门
        SysDept sysDept = sysDeptMapper.selectById(id);
        if (sysDept == null) {
            ExceptionCast.cast(BaseServerCode.RESULT_DATA_NONE);
        }
        // 执行删除
        int row = sysDeptMapper.deleteById(id);
        if (row != 1) {
           ExceptionCast.cast(BaseServerCode.DELETE_ERROR);
        }
        log.info("### 部门删除完毕 ###");
    }

	/**
     * 部门修改
     * @param id
     * @param sysDeptSaveOrUpdateVO
     */
    @Override
    public SysDeptTreeVO update(String id, SysDeptSaveOrUpdateVO sysDeptSaveOrUpdateVO) {
        // 参数校验
        if (StringUtils.isBlank(id) || sysDeptSaveOrUpdateVO == null) {
           ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        // 根据ID查询部门
        SysDept sysDept = sysDeptMapper.selectById(id);
        if (sysDept == null) {
            ExceptionCast.cast(BaseServerCode.RESULT_DATA_NONE);
        }
        // 属性赋值
        BeanUtils.copyProperties(sysDeptSaveOrUpdateVO, sysDept);
        sysDept.setId(id);
        // 父级部门不能为自己
        if (StringUtils.equals(id, sysDeptSaveOrUpdateVO.getParentId())) {
            ExceptionCast.cast(BaseServerCode.PARENT_IS_SELF);
        }
        // 执行修改
        int row = sysDeptMapper.updateById(sysDept);
        if (row != 1) {
           ExceptionCast.cast(BaseServerCode.DELETE_ERROR);
        }
        // 转换为VO对象
        SysDeptTreeVO sysDeptTreeVO = new SysDeptTreeVO();
        BeanUtils.copyProperties(sysDept, sysDeptTreeVO);
         log.info("### 部门修改完毕 ###");
        // 返回保存后结果
        return sysDeptTreeVO;
    }

   /**
    * 根据部门ID查询
    * @param id
    */
	@Override
	public SysDeptTreeVO findById(String id) {
	    // 参数校验
        if (StringUtils.isBlank(id)) {
           ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        // 根据ID查询部门
		SysDept sysDept = sysDeptMapper.selectById(id);
		if (sysDept == null) {
            return null;
        }
        // 转换为VO对象
        SysDeptTreeVO sysDeptTreeVO = new SysDeptTreeVO();
        BeanUtils.copyProperties(sysDept, sysDeptTreeVO);
        log.info("### 部门查询完毕 ###");
        // 返回保存后结果
        return sysDeptTreeVO;
	}

    /**
     * 部门树列表查询
     * @return
     */
    @Override
    public List<SysDeptTreeVO> getTreeList() {
        List<SysDeptTreeVO> deptTreeVOList = new ArrayList<>();
        // 查询所有顶级节点
        LambdaQueryWrapper<SysDept> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.isNull(SysDept::getParentId)
                .orderByAsc(SysDept::getSort);
        List<SysDept> rootDeptList = sysDeptMapper.selectList(queryWrapper);
        log.info("### 顶级部门树列表查询完毕 ###");
        rootDeptList.forEach(rootDept -> {
            // 转换SysDeptTreeVO
            SysDeptTreeVO treeNodeVO = new SysDeptTreeVO();
            BeanUtils.copyProperties(rootDept, treeNodeVO);
            treeNodeVO.setLabel(rootDept.getName());
            treeNodeVO.setChildren(findChildrenByParentId(rootDept.getId()));

            deptTreeVOList.add(treeNodeVO);
        });
        return deptTreeVOList;
    }
    // 递归查询子节点
    private List<SysDeptTreeVO> findChildrenByParentId(String parentId) {
        if (StringUtils.isBlank(parentId)) {
            return null;
        }
        // 查询所有子部门
        LambdaQueryWrapper<SysDept> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysDept::getParentId, parentId)
                .orderByAsc(SysDept::getSort);
        List<SysDept> sysDeptList = sysDeptMapper.selectList(queryWrapper);
        if (CollUtil.isEmpty(sysDeptList)) {
            return null;
        }
        List<SysDeptTreeVO> children = new ArrayList<>();
        sysDeptList.forEach(dept -> {
            // 转换SysDeptTreeVO
            SysDeptTreeVO treeNodeVO = new SysDeptTreeVO();
            BeanUtils.copyProperties(dept, treeNodeVO);
            treeNodeVO.setLabel(dept.getName());
            treeNodeVO.setChildren(findChildrenByParentId(dept.getId()));
            children.add(treeNodeVO);
        });
        return children;
    }
}
