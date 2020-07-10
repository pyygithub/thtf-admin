package com.thtf.base.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.thtf.base.api.entity.*;
import com.thtf.base.api.vo.SysMenuVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ---------------------------
 * 菜单 (SysMenuMapper)         
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020-01-07 11:13:01
 * 版本：  v1.0
 * ---------------------------
 */
public interface SysMenuMapper extends BaseMapper<SysMenu>{
    List<SysMenuVO> selectMenuListByRoleIds(@Param("roleIds") List<String> roleIds);
}