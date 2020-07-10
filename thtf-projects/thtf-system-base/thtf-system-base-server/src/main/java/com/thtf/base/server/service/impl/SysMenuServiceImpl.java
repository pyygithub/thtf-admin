package com.thtf.base.server.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.thtf.base.api.entity.SysMenu;
import com.thtf.base.api.entity.SysRoleMenu;
import com.thtf.base.api.entity.SysUserRole;
import com.thtf.base.api.vo.*;
import com.thtf.base.server.constants.BaseServerCode;
import com.thtf.base.server.constants.SystemConstant;
import com.thtf.base.server.mapper.SysMenuMapper;
import com.thtf.base.server.mapper.SysRoleMenuMapper;
import com.thtf.base.server.mapper.SysUserRoleMapper;
import com.thtf.base.server.service.SysMenuService;
import com.thtf.common.core.constant.CommonConstant;
import com.thtf.common.core.exception.ExceptionCast;
import com.thtf.common.core.properties.JwtProperties;
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

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * ---------------------------
 * 菜单 (SysMenuServiceImpl)         
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020-01-07 11:13:01
 * 版本：  v1.0
 * ---------------------------
 */
@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private JwtProperties jwtProperties;

	@Autowired
	private SysMenuMapper sysMenuMapper;

	@Autowired
    private SysUserRoleMapper sysUserRoleMapper;

	@Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    /**
     * 菜单保存
     * @param sysMenuSaveOrUpdateVO
     */
	@Override
	public SysMenuVO save(SysMenuSaveOrUpdateVO sysMenuSaveOrUpdateVO) {
        // 初始化Model对象
        SysMenu sysMenu = new SysMenu();
        // 属性赋值
        BeanUtils.copyProperties(sysMenuSaveOrUpdateVO, sysMenu);
        sysMenu.setId(null); // 确保ID为null，默认使用mybatis-plus ID生成策略
        // 执行保存
        int row = sysMenuMapper.insert(sysMenu);
        if (row != 1) {
            ExceptionCast.cast(BaseServerCode.SAVE_ERROR);
        }
        // 转换为VO对象
        SysMenuVO sysMenuVO = new SysMenuVO();
        BeanUtils.copyProperties(sysMenu, sysMenuVO);
        log.info("### 菜单保存完毕 ###");
        // 返回保存后结果
        return sysMenuVO;
	}

    /**
     * 菜单删除
     * @param id
     */
    @Override
    public void delete(String id) {
        // 参数校验
        if (StringUtils.isBlank(id)) {
           ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        // 根据ID查询菜单
        SysMenu sysMenu = sysMenuMapper.selectById(id);
        if (sysMenu == null) {
            ExceptionCast.cast(BaseServerCode.RESULT_DATA_NONE);
        }
        // 执行删除
        int row = sysMenuMapper.deleteById(id);
        if (row != 1) {
           ExceptionCast.cast(BaseServerCode.DELETE_ERROR);
        }
        log.info("### 菜单删除完毕 ###");
    }

	/**
     * 菜单修改
     * @param id
     * @param sysMenuSaveOrUpdateVO
     */
    @Override
    public SysMenuVO update(String id, SysMenuSaveOrUpdateVO sysMenuSaveOrUpdateVO) {
        // 参数校验
        if (StringUtils.isBlank(id) || sysMenuSaveOrUpdateVO == null) {
           ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        // 根据ID查询菜单
        SysMenu sysMenu = sysMenuMapper.selectById(id);
        if (sysMenu == null) {
            ExceptionCast.cast(BaseServerCode.RESULT_DATA_NONE);
        }

        // 属性赋值
        BeanUtils.copyProperties(sysMenuSaveOrUpdateVO, sysMenu);
        sysMenu.setId(id);
        // 执行修改
        int row = sysMenuMapper.updateById(sysMenu);
        if (row != 1) {
           ExceptionCast.cast(BaseServerCode.DELETE_ERROR);
        }
        // 转换为VO对象
        SysMenuVO sysMenuVO = new SysMenuVO();
        BeanUtils.copyProperties(sysMenu, sysMenuVO);
         log.info("### 菜单修改完毕 ###");
        // 返回保存后结果
        return sysMenuVO;
    }

   /**
    * 根据菜单ID查询
    * @param id
    */
	@Override
	public SysMenuVO findById(String id) {
	    // 参数校验
        if (StringUtils.isBlank(id)) {
           ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        // 根据ID查询菜单
		SysMenu sysMenu = sysMenuMapper.selectById(id);
		if (sysMenu == null) {
            return null;
        }
        // 转换为VO对象
        SysMenuVO sysMenuVO = new SysMenuVO();
        BeanUtils.copyProperties(sysMenu, sysMenuVO);
        log.info("### 菜单查询完毕 ###");
        // 返回保存后结果
        return sysMenuVO;
	}

   /**
     * 菜单模糊查询
     * @param queryConditionVO
     * @return
     */
    @Override
    public List<SysMenuVO> findList(SysMenuQueryConditionVO queryConditionVO) {
        // 参数校验
        if (queryConditionVO == null) {
          queryConditionVO = new SysMenuQueryConditionVO();
        }
        // 查询条件
        LambdaQueryWrapper<SysMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(queryConditionVO.getName()), SysMenu::getName, queryConditionVO.getName());
        // 执行查询
        List<SysMenu> sysMenuList = sysMenuMapper.selectList(queryWrapper);
        log.info("### 菜单Model模糊查询完毕，总条数：{}条###", sysMenuList.size());
        // 菜单转换VO数据
        List<SysMenuVO> sysMenuVOList = new ArrayList<>();
        sysMenuList.forEach(sysMenu -> {
            SysMenuVO sysMenuVO = new SysMenuVO();
            BeanUtils.copyProperties(sysMenu, sysMenuVO);
            sysMenuVOList.add(sysMenuVO);
        });
        log.info("### 菜单转换VO数据完毕###");
        return sysMenuVOList;
    }

    /**
     * 菜单分页模糊查询
     * @param queryConditionVO
     * @param page
     * @param size
     * @return
     */
	@Override
    public Pager<SysMenuVO> findList(SysMenuQueryConditionVO queryConditionVO, int page, int size) {
        // 参数校验
        if (queryConditionVO == null) {
          queryConditionVO = new SysMenuQueryConditionVO();
        }
        // 查询条件
        LambdaQueryWrapper<SysMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(queryConditionVO.getName()), SysMenu::getName, queryConditionVO.getName());
        // 分页条件
        Page<SysMenu> pageInfo = new Page(page, size);
        // 执行查询
        IPage<SysMenu> sysMenuPage = sysMenuMapper.selectPage(pageInfo, queryWrapper);
        long total = sysMenuPage.getTotal();
        List<SysMenu> sysMenuList = sysMenuPage.getRecords();
        // 菜单转换VO数据
        List<SysMenuVO> sysMenuVOList = new ArrayList<>();
        sysMenuList.forEach(sysMenu -> {
            SysMenuVO sysMenuVO = new SysMenuVO();
            BeanUtils.copyProperties(sysMenu, sysMenuVO);
            sysMenuVOList.add(sysMenuVO);
        });
        log.info("### 菜单转换VO数据完毕###");
        // 分装分页查询结果
        return new Pager(total, sysMenuVOList);
    }

    /**
     * 菜单树列表查询
     * @param queryConditionVO
     * @return
     */
    @Override
    public List<SysMenuTreeVO> findTreeList(SysMenuQueryConditionVO queryConditionVO) {
        List<SysMenuTreeVO> menuTreeVOList = new ArrayList<>();
        // 查询所有顶级节点
        LambdaQueryWrapper<SysMenu> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.isNull(SysMenu::getParentId)
                .orderByAsc(SysMenu::getSort);
        List<SysMenu> rootMenuList = sysMenuMapper.selectList(queryWrapper);
            log.info("### 顶级菜单树列表查询完毕 ###");
        rootMenuList.forEach(rootDept -> {
            // SysMenuTreeVO
            SysMenuTreeVO treeNodeVO = new SysMenuTreeVO();
            BeanUtils.copyProperties(rootDept, treeNodeVO);
            treeNodeVO.setLabel(rootDept.getName());
            treeNodeVO.setChildren(findChildrenByParentId(rootDept.getId()));

            menuTreeVOList.add(treeNodeVO);
        });
        return menuTreeVOList;
    }

    /**
     * 菜单路由列表查询（根据用户ID）
     * @return
     */
    @Override
    public List<SysMenuRouteVO> getRouteMenus(HttpServletRequest request) {
        String token = request.getHeader(jwtProperties.getTokenKey());
        if (StrUtil.isBlank(token)) {
            ExceptionCast.cast(CommonCode.UNAUTHENTICATED);
        }
//        Claims claims = JwtUtil.parseToken(token, jwtProperties.getBase64Secret());
//        String userId = JwtUtil.getUserId(claims);
        // todo 添加获取用户信息
        String userId = "";
        log.info("### 当前登录用户ID：{} ###", userId);
        List<String> roleIds = findRoleByUserId(userId);

        // 查询角色关联所有顶级菜单节点ID集合
        if (CollUtil.isNotEmpty(roleIds)) {
            LambdaQueryWrapper<SysRoleMenu> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(SysRoleMenu::getRoleId, roleIds);
            List<SysRoleMenu> sysRoleMenus = sysRoleMenuMapper.selectList(queryWrapper);
            List<String> menuIds = sysRoleMenus.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());
            if (menuIds != null) {
                LambdaQueryWrapper<SysMenu> menuQueryWrapper = new LambdaQueryWrapper<>();
                menuQueryWrapper.in(SysMenu::getId, menuIds)
                        .in(SysMenu::getType, Arrays.asList(SystemConstant.DIRECTORY, SystemConstant.MENU))
                        .eq(SysMenu::getDeletedFlag, CommonConstant.UN_DELETED_FLAG);
                List<SysMenu> sysMenuList = sysMenuMapper.selectList(menuQueryWrapper);
                // 将sysMenuList转换为sysMenuTreeVOList
                List<SysMenuTreeVO> sysMenuTreeVOList = new ArrayList<>();
                sysMenuList.forEach(sysMenu -> {
                    SysMenuTreeVO menuTreeVO = new SysMenuTreeVO();
                    BeanUtils.copyProperties(sysMenu, menuTreeVO);
                    sysMenuTreeVOList.add(menuTreeVO);
                });

                // 转换菜单树
                List<SysMenuTreeVO> sysMenuTreeList = buildTree(sysMenuTreeVOList);
                // 转换路由树
                List<SysMenuRouteVO> sysMenuRouteVOList = buildMenus(sysMenuTreeList);
                return sysMenuRouteVOList;
            }
        }
        return null;
    }

    // 将List<SysMenuTreeVO>转换为List<SysMenuRouteVO>
    private List<SysMenuRouteVO> buildMenus(List<SysMenuTreeVO> sysMenuTreeList) {
        List<SysMenuRouteVO> list = new LinkedList<>();
        sysMenuTreeList.forEach(menuTreeVO -> {
            if (menuTreeVO != null){
                List<SysMenuTreeVO> menuTreeVOList = menuTreeVO.getChildren();
                SysMenuRouteVO menuRouteVO = new SysMenuRouteVO();
                menuRouteVO.setName(ObjectUtil.isNotEmpty(menuTreeVO.getComponentName())  ? menuTreeVO.getComponentName() : menuTreeVO.getName());
                // 一级目录需要加斜杠，不然会报警告
                menuRouteVO.setPath(menuTreeVO.getParentId() == null && !menuTreeVO.getPath().startsWith("/") ? "/" + menuTreeVO.getPath() : menuTreeVO.getPath());
                menuRouteVO.setHidden(menuTreeVO.getHidden() == SystemConstant.HIDDEN);
                // 如果不是外链
                if(menuTreeVO.getIframe() == SystemConstant.NOT_EXTERNAL){
                    // 一级菜单
                    if(menuTreeVO.getParentId() == null){
                        menuRouteVO.setComponent(StrUtil.isEmpty(menuTreeVO.getComponentPath()) ? "Layout" : menuTreeVO.getComponentPath());
                    } else if(StrUtil.isNotBlank(menuTreeVO.getComponentPath())){
                        menuRouteVO.setComponent(menuTreeVO.getComponentPath());
                    }
                }
                menuRouteVO.setMeta(new SysMenuMetaVO(menuTreeVO.getName(),menuTreeVO.getIcon(), menuTreeVO.getCache() == SystemConstant.CACHE));
                if(CollUtil.isNotEmpty(menuTreeVOList)){
                    menuRouteVO.setAlwaysShow(true);
                    menuRouteVO.setRedirect("noredirect");
                    menuRouteVO.setChildren(buildMenus(menuTreeVOList));
                    // 处理是一级菜单并且没有子菜单的情况
                } else if(menuTreeVO.getParentId() == null){
                    SysMenuRouteVO menuRouteVO1 = new SysMenuRouteVO();
                    menuRouteVO1.setMeta(menuRouteVO.getMeta());
                    // 非外链
                    if(menuTreeVO.getIframe() == SystemConstant.NOT_EXTERNAL){
                        menuRouteVO1.setPath("index");
                        menuRouteVO1.setName(menuRouteVO.getName());
                        menuRouteVO1.setComponent(menuRouteVO.getComponent());
                    } else {
                        menuRouteVO1.setPath(menuTreeVO.getPath());
                    }
                    menuRouteVO.setName(null);
                    menuRouteVO.setMeta(null);
                    menuRouteVO.setComponent("Layout");
                    List<SysMenuRouteVO> list1 = new ArrayList<>();
                    list1.add(menuRouteVO1);
                    menuRouteVO.setChildren(list1);
                }
                list.add(menuRouteVO);
            }
        });
        return list;
    }

    // 将sysMenuList转换为tree结构
    private List<SysMenuTreeVO> buildTree(List<SysMenuTreeVO> sysMenuList) {
        List<SysMenuTreeVO> trees = new ArrayList<>();
        Set<String> ids = new HashSet<>();
        for (SysMenuTreeVO menuTreeVO : sysMenuList) {
            if (menuTreeVO.getParentId() == null) {
                trees.add(menuTreeVO);
            }
            for (SysMenuTreeVO it : sysMenuList) {
                if (StrUtil.equals(it.getParentId(), menuTreeVO.getId())) {
                    if (menuTreeVO.getChildren() == null) {
                        menuTreeVO.setChildren(new ArrayList<>());
                    }
                    menuTreeVO.getChildren().add(it);
                    ids.add(it.getId());
                }
            }
        }
        Map<String,Object> map = new HashMap<>();
        if(trees.size() == 0){
            trees = sysMenuList.stream().filter(s -> !ids.contains(s.getId())).collect(Collectors.toList());
        }
        return trees;
    }

    // 查询当前登录用户拥有角色ID
    private List<String> findRoleByUserId(String userId) {
        LambdaQueryWrapper<SysUserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysUserRole::getUserId, userId);
        List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectList(queryWrapper);
        List<String> roleIds = sysUserRoles.stream().map(SysUserRole::getRoleId).collect(Collectors.toList());
        log.info("### 当前用户拥有角色ID集合：{} ###", roleIds);
        return roleIds;
    }

    // 递归查询子节点
    private List<SysMenuTreeVO> findChildrenByParentId(String parentId) {
        if (StringUtils.isBlank(parentId)) {
            return null;
        }
        // 查询所有子部门
        LambdaQueryWrapper<SysMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysMenu::getParentId, parentId)
                .orderByAsc(SysMenu::getSort);
        List<SysMenu> sysMenuList = sysMenuMapper.selectList(queryWrapper);
        if (CollUtil.isEmpty(sysMenuList)) {
            return null;
        }
        List<SysMenuTreeVO> children = new ArrayList<>();
        sysMenuList.forEach(dept -> {
            // 转换SysMenuTreeVO
            SysMenuTreeVO treeNodeVO = new SysMenuTreeVO();
            BeanUtils.copyProperties(dept, treeNodeVO);
            treeNodeVO.setLabel(dept.getName());
            treeNodeVO.setChildren(findChildrenByParentId(dept.getId()));
            children.add(treeNodeVO);
        });
        return children;
    }
}
