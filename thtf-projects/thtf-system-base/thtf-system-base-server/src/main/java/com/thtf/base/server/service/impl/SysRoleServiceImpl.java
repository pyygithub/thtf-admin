package com.thtf.base.server.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.thtf.base.server.constants.BaseServerCode;
import com.thtf.base.server.mapper.SysRoleMapper;
import com.thtf.base.server.mapper.SysRoleMenuMapper;
import com.thtf.base.server.service.SysRoleService;
import com.thtf.common.core.exception.ExceptionCast;
import com.thtf.common.core.response.CommonCode;
import com.thtf.common.core.response.Pager;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.thtf.base.api.entity.*;
import com.thtf.base.api.vo.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ---------------------------
 * 角色 (SysRoleServiceImpl)         
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020-01-09 14:55:26
 * 版本：  v1.0
 * ---------------------------
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
public class SysRoleServiceImpl implements SysRoleService {

	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    /**
     * 角色保存
     * @param sysRoleSaveOrUpdateVO
     */
	@Override
	public SysRoleVO save(SysRoleSaveOrUpdateVO sysRoleSaveOrUpdateVO) {
        // 初始化Model对象
        SysRole sysRole = new SysRole();
        // 属性赋值
        BeanUtils.copyProperties(sysRoleSaveOrUpdateVO, sysRole);
        sysRole.setId(null); // 确保ID为null，默认使用mybatis-plus ID生成策略
        // 执行保存
        int row = sysRoleMapper.insert(sysRole);
        if (row != 1) {
            ExceptionCast.cast(BaseServerCode.SAVE_ERROR);
        }
        log.info("### 角色表数据添加完毕 ###");
        // 转换为VO对象
        SysRoleVO sysRoleVO = new SysRoleVO();
        BeanUtils.copyProperties(sysRole, sysRoleVO);
        log.info("### 角色保存完毕 ###");
        // 返回保存后结果
        return sysRoleVO;
	}

    /**
     * 角色删除
     * @param id
     */
    @Override
    public void delete(String id) {
        // 参数校验
        if (StringUtils.isBlank(id)) {
           ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        // 根据ID查询角色
        SysRole sysRole = sysRoleMapper.selectById(id);
        if (sysRole == null) {
            ExceptionCast.cast(BaseServerCode.RESULT_DATA_NONE);
        }
        // 删除角色关联权限信息
        LambdaQueryWrapper<SysRoleMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysRoleMenu::getRoleId, id);
        sysRoleMenuMapper.delete(queryWrapper);
        log.info("### 角色关联权限删除完毕 ###");
        // 执行删除
        int row = sysRoleMapper.deleteById(id);
        if (row != 1) {
           ExceptionCast.cast(BaseServerCode.DELETE_ERROR);
        }
        log.info("### 角色删除完毕 ###");

    }

    @Override
    public void deleteBatch(List<String> ids) {
        // 参数校验
        if (CollUtil.isEmpty(ids)) {
            ExceptionCast.cast(BaseServerCode.DEL_IDS_ISEMPTY);
        }
        // 删除角色关联权限信息
        for (String roleId : ids) {
            LambdaQueryWrapper<SysRoleMenu> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(SysRoleMenu::getRoleId, roleId);
            sysRoleMenuMapper.delete(queryWrapper);
        }
        log.info("### 角色关联权限删除完毕 ###");
        // 批量删除角色
        int rows = sysRoleMapper.deleteBatchIds(ids);
        if (rows < 1) {
            ExceptionCast.cast(BaseServerCode.DELETE_ERROR);
        }
    }

    /**
     * 角色修改
     * @param id
     * @param sysRoleSaveOrUpdateVO
     */
    @Override
    public SysRoleVO update(String id, SysRoleSaveOrUpdateVO sysRoleSaveOrUpdateVO) {
        // 参数校验
        if (StringUtils.isBlank(id) || sysRoleSaveOrUpdateVO == null) {
           ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        // 根据ID查询角色
        SysRole sysRole = sysRoleMapper.selectById(id);
        if (sysRole == null) {
            ExceptionCast.cast(BaseServerCode.RESULT_DATA_NONE);
        }
        // 属性赋值
        BeanUtils.copyProperties(sysRoleSaveOrUpdateVO, sysRole);
        sysRole.setId(id);
        // 执行修改
        int row = sysRoleMapper.updateById(sysRole);
        if (row != 1) {
           ExceptionCast.cast(BaseServerCode.DELETE_ERROR);
        }
        // 转换为VO对象
        SysRoleVO sysRoleVO = new SysRoleVO();
        BeanUtils.copyProperties(sysRole, sysRoleVO);
         log.info("### 角色修改完毕 ###");
        // 返回保存后结果
        return sysRoleVO;
    }

   /**
    * 根据角色ID查询
    * @param id
    */
	@Override
	public SysRoleVO findById(String id) {
	    // 参数校验
        if (StringUtils.isBlank(id)) {
           ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        // 根据ID查询角色
		SysRole sysRole = sysRoleMapper.selectById(id);
		if (sysRole == null) {
            return null;
        }
		// 获取角色关联菜单ID集合
        LambdaQueryWrapper<SysRoleMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysRoleMenu::getRoleId, id);
        List<SysRoleMenu> sysRoleMenus = sysRoleMenuMapper.selectList(queryWrapper);
        List<String> menuIds = sysRoleMenus.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());
        log.info("### 角色关联权限ID集合：{} ###", menuIds);

        // 转换为VO对象
        SysRoleVO sysRoleVO = new SysRoleVO();
        BeanUtils.copyProperties(sysRole, sysRoleVO);
        sysRoleVO.setMenuIds(menuIds);
        log.info("### 角色查询完毕 ###");
        // 返回保存后结果
        return sysRoleVO;
	}

   /**
     * 角色模糊查询
     * @param queryConditionVO
     * @return
     */
    @Override
    public List<SysRoleVO> findList(SysRoleQueryConditionVO queryConditionVO) {
        // 参数校验
        if (queryConditionVO == null) {
          queryConditionVO = new SysRoleQueryConditionVO();
        }
        // 查询条件
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(queryConditionVO.getName()), SysRole::getName, queryConditionVO.getName());
        // 执行查询
        List<SysRole> sysRoleList = sysRoleMapper.selectList(queryWrapper);
        log.info("### 角色Model模糊查询完毕，总条数：{}条###", sysRoleList.size());
        // 角色转换VO数据
        List<SysRoleVO> sysRoleVOList = new ArrayList<>();
        sysRoleList.forEach(sysRole -> {
            SysRoleVO sysRoleVO = new SysRoleVO();
            BeanUtils.copyProperties(sysRole, sysRoleVO);
            sysRoleVOList.add(sysRoleVO);
        });
        log.info("### 角色转换VO数据完毕###");
        return sysRoleVOList;
    }

    /**
     * 角色分页模糊查询
     * @param queryConditionVO
     * @param page
     * @param size
     * @return
     */
	@Override
    public Pager<SysRoleVO> findList(SysRoleQueryConditionVO queryConditionVO, int page, int size) {
        // 参数校验
        if (queryConditionVO == null) {
          queryConditionVO = new SysRoleQueryConditionVO();
        }
        // 查询条件
        LambdaQueryWrapper<SysRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(queryConditionVO.getName()), SysRole::getName, queryConditionVO.getName());
        // 分页条件
        Page<SysRole> pageInfo = new Page(page, size);
        // 执行查询
        IPage<SysRole> sysRolePage = sysRoleMapper.selectPage(pageInfo, queryWrapper);
        long total = sysRolePage.getTotal();
        List<SysRole> sysRoleList = sysRolePage.getRecords();
        // 角色转换VO数据
        List<SysRoleVO> sysRoleVOList = new ArrayList<>();
        sysRoleList.forEach(sysRole -> {
            SysRoleVO sysRoleVO = new SysRoleVO();
            BeanUtils.copyProperties(sysRole, sysRoleVO);
            sysRoleVOList.add(sysRoleVO);
        });
        log.info("### 角色转换VO数据完毕###");
        // 分装分页查询结果
        return new Pager(total, sysRoleVOList);
    }

    @Override
    public void setPermissions(String id, List<String> menuIds) {
        // 删除角色关联权限信息
        LambdaQueryWrapper<SysRoleMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysRoleMenu::getRoleId, id);
        sysRoleMenuMapper.delete(queryWrapper);
        log.info("### 角色关联权限删除完毕 ###");
        // 重新添加新的关联关系
        addRoleMenus(id, menuIds);
    }

    // 添加角色权限关联关系
    private void addRoleMenus(String roleId, List<String> menuIds) {
        if (CollUtil.isNotEmpty(menuIds)) {
            for (String menuId : menuIds) {
                SysRoleMenu sysRoleMenu = new SysRoleMenu();
                sysRoleMenu.setId(null);
                sysRoleMenu.setRoleId(roleId);
                sysRoleMenu.setMenuId(menuId);
                sysRoleMenuMapper.insert(sysRoleMenu);
            }
            log.info("### 角色角色关联菜单数据添加完毕 ###");
        }
    }
}
