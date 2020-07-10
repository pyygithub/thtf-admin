package com.thtf.base.server.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.thtf.base.api.entity.SysUser;
import com.thtf.base.api.entity.SysUserRole;
import com.thtf.base.api.vo.*;
import com.thtf.base.server.constants.BaseServerCode;
import com.thtf.base.server.mapper.SysMenuMapper;
import com.thtf.base.server.mapper.SysUserMapper;
import com.thtf.base.server.mapper.SysUserRoleMapper;
import com.thtf.base.server.service.SysUserService;
import com.thtf.common.core.exception.ExceptionCast;
import com.thtf.common.core.response.CommonCode;
import com.thtf.common.core.response.Pager;
import com.thtf.common.core.utils.SpringSecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * ---------------------------
 * 用户 (SysUserServiceImpl)         
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020-01-10 16:06:25
 * 版本：  v1.0
 * ---------------------------
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
    private SysMenuMapper sysMenuMapper;

	@Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 用户保存
     * @param sysUserSaveOrUpdateVO
     */
	@Override
	public SysUserVO save(SysUserSaveOrUpdateVO sysUserSaveOrUpdateVO) {
        // 初始化Model对象
        SysUser sysUser = new SysUser();
        // 属性赋值
        BeanUtils.copyProperties(sysUserSaveOrUpdateVO, sysUser);
        sysUser.setId(null); // 确保ID为null，默认使用mybatis-plus ID生成策略
        // 密码加密
        String encoderPassword = SpringSecurityUtil.encoderPassword(sysUser.getPassword());
        sysUser.setPassword(encoderPassword);
        // 执行保存
        int row = sysUserMapper.insert(sysUser);
        if (row != 1) {
            ExceptionCast.cast(BaseServerCode.SAVE_ERROR);
        }
        // 保存用户关联角色
        List<String> roleIds = sysUserSaveOrUpdateVO.getRoleIds();
        if (CollUtil.isNotEmpty(roleIds)) {
            roleIds.forEach(roleId -> {
                SysUserRole userRole = new SysUserRole();
                userRole.setRoleId(roleId);
                userRole.setUserId(sysUser.getId());
                sysUserRoleMapper.insert(userRole);
            });
            log.info("### 用户角色关联关系保存成功 ###");
        }
        // 转换为VO对象
        SysUserVO sysUserVO = new SysUserVO();
        BeanUtils.copyProperties(sysUser, sysUserVO);
        log.info("### 用户保存完毕 ###");
        // 返回保存后结果
        return sysUserVO;
	}

    /**
     * 用户删除
     * @param id
     */
    @Override
    public void delete(String id) {
        // 参数校验
        if (StringUtils.isBlank(id)) {
           ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        // 根据ID查询用户
        SysUser sysUser = sysUserMapper.selectById(id);
        if (sysUser == null) {
            ExceptionCast.cast(BaseServerCode.RESULT_DATA_NONE);
        }
        // 删除用户角色关联关系
        LambdaQueryWrapper<SysUserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUserRole::getUserId, id);
        sysUserRoleMapper.delete(queryWrapper);
        log.info("### 用户角色关联关系删除完毕 ###");

        // 删除用户
        int row = sysUserMapper.deleteById(id);
        if (row != 1) {
           ExceptionCast.cast(BaseServerCode.DELETE_ERROR);
        }
        log.info("### 用户删除完毕 ###");
    }

    @Override
    public void deleteBatch(List<String> ids) {
        // 参数校验
        if (CollUtil.isEmpty(ids)) {
            ExceptionCast.cast(BaseServerCode.DEL_IDS_ISEMPTY);
        }
        ids.forEach(userId -> {
            delete(userId);
        });
    }

	/**
     * 用户修改
     * @param id
     * @param sysUserSaveOrUpdateVO
     */
    @Override
    public SysUserVO update(String id, SysUserSaveOrUpdateVO sysUserSaveOrUpdateVO) {
        // 参数校验
        if (StringUtils.isBlank(id) || sysUserSaveOrUpdateVO == null) {
           ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        // 根据ID查询用户
        SysUser sysUser = sysUserMapper.selectById(id);
        if (sysUser == null) {
            ExceptionCast.cast(BaseServerCode.RESULT_DATA_NONE);
        }
        // 属性赋值
        sysUser.setName(sysUserSaveOrUpdateVO.getName());
        sysUser.setUsername(sysUserSaveOrUpdateVO.getUsername());
        sysUser.setDeptId(sysUserSaveOrUpdateVO.getDeptId());
        sysUser.setJobId(sysUserSaveOrUpdateVO.getJobId());
        sysUser.setEmail(sysUserSaveOrUpdateVO.getEmail());
        sysUser.setPhone(sysUserSaveOrUpdateVO.getPhone());
        sysUser.setAvatar(sysUserSaveOrUpdateVO.getAvatar());
        sysUser.setStatus(sysUserSaveOrUpdateVO.getStatus());
        // 执行修改
        int row = sysUserMapper.updateById(sysUser);
        if (row != 1) {
           ExceptionCast.cast(BaseServerCode.DELETE_ERROR);
        }

        // 删除用户角色关联关系
        LambdaQueryWrapper<SysUserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUserRole::getUserId, id);
        sysUserRoleMapper.delete(queryWrapper);
        log.info("### 用户角色关联关系删除完毕 ###");

        List<String> roleIds = sysUserSaveOrUpdateVO.getRoleIds();
        if (CollUtil.isNotEmpty(roleIds)) {
            // 添加新的用户角色关联关系
            roleIds.forEach(roleId -> {
                SysUserRole userRole = new SysUserRole();
                userRole.setUserId(id);
                userRole.setRoleId(roleId);
                sysUserRoleMapper.insert(userRole);
            });
            log.info("### 用户角色关联关系更新完毕 ###");
        }
        // 转换为VO对象
        SysUserVO sysUserVO = new SysUserVO();
        BeanUtils.copyProperties(sysUser, sysUserVO);
         log.info("### 用户修改完毕 ###");
        // 返回保存后结果
        return sysUserVO;
    }

   /**
    * 根据用户ID查询
    * @param id
    */
	@Override
	public SysUserVO findById(String id) {
	    // 参数校验
        if (StringUtils.isBlank(id)) {
           ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        // 根据ID查询用户
		SysUserVO sysUserVO = sysUserMapper.selectUserById(id);
        log.info("### 用户查询完毕 ###");
        // 返回保存后结果
        return sysUserVO;
	}

   /**
     * 用户模糊查询
     * @param queryConditionVO
     * @return
     */
    @Override
    public List<SysUserVO> findList(SysUserQueryConditionVO queryConditionVO) {
        // 参数校验
        if (queryConditionVO == null) {
          queryConditionVO = new SysUserQueryConditionVO();
        }
        // 执行查询
        List<SysUserVO> sysUserVOList = sysUserMapper.selectUserList(queryConditionVO);
        log.info("### 用户模糊查询完毕，总条数：{}条###", sysUserVOList.size());
        return sysUserVOList;
    }

    /**
     * 用户分页模糊查询
     * @param queryConditionVO
     * @param page
     * @param size
     * @return
     */
	@Override
    public Pager<SysUserVO> findList(SysUserQueryConditionVO queryConditionVO, int page, int size) {
        // 参数校验
        if (queryConditionVO == null) {
          queryConditionVO = new SysUserQueryConditionVO();
        }
        // 分页条件
        Page<SysUserVO> pageInfo = new Page(page, size);
        // 执行查询
        List<SysUserVO> sysUserVOList = sysUserMapper.selectUserList(pageInfo, queryConditionVO);
        pageInfo.setRecords(sysUserVOList);
        long total = pageInfo.getTotal();
        // 分装分页查询结果
        return new Pager(total, sysUserVOList);
    }

    /**
     * 根据用户名查询用户认证信息
     * @param username
     * @return
     */
    @Override
    public UserDetailsVO findByUsername(String username) {
        SysUserVO sysUserVO = sysUserMapper.selectUserByUsername(username);
        if (sysUserVO == null) {
            ExceptionCast.cast(CommonCode.USERNAME_NOT_EXISTS);
        }
        Set<String> permissions = null;
        // 取出当前用户拥有所有角色信息
        List<SysRoleVO> roleList = sysUserVO.getRoleList();
        // 根据角色ids查询关联权限信息
        if (CollUtil.isNotEmpty(roleList)) {
            List<String> roleIds = roleList.stream().map(SysRoleVO::getId).collect(Collectors.toList());
            List<SysMenuVO> menuVOList = sysMenuMapper.selectMenuListByRoleIds(roleIds);
            List<String> permissionList = menuVOList.stream().map(SysMenuVO::getPermission).collect(Collectors.toList());
            permissionList = permissionList.stream().filter(permission -> StrUtil.isNotBlank(permission)).collect(Collectors.toList());
            permissions = new HashSet(permissionList);
        }
        UserDetailsVO userDetailsVO = new UserDetailsVO();
        userDetailsVO.setSysUser(sysUserVO);
        userDetailsVO.setPermissions(permissions);
        return userDetailsVO;
    }
}
