package com.thtf.common.auth.handler;

import cn.hutool.core.util.CharsetUtil;
import com.alibaba.fastjson.JSON;
import com.thtf.common.core.response.ResponseResult;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ---------------------------
 * 没有权限 授权失败时返回信息
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020/1/21 16:04
 * 版本：  v1.0
 * ---------------------------
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(401);
        responseResult.setMessage("抱歉,没有权限,请联系管理员");
        responseResult.setTimestamp(System.currentTimeMillis());
        response.setCharacterEncoding(CharsetUtil.UTF_8);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().write(JSON.toJSONString(responseResult));
    }
}
