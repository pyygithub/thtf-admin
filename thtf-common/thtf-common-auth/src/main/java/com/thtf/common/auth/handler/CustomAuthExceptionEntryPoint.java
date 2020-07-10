package com.thtf.common.auth.handler;

import cn.hutool.core.util.CharsetUtil;
import com.alibaba.fastjson.JSON;
import com.thtf.common.core.response.ResponseResult;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ---------------------------
 * 自定义Token错误异常信息
 * ---------------------------
 * 作者：  pyy
 * 时间：  2020/1/21 15:54
 * 版本：  v1.0
 * ---------------------------
 */
public class CustomAuthExceptionEntryPoint implements AuthenticationEntryPoint {

    /**
     * token 错误时会进入这里
     * @param request
     * @param response
     * @param authException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(401);
        responseResult.setMessage("非法访问资源,访问此资源需要完全身份验证");
        responseResult.setTimestamp(System.currentTimeMillis());
        response.setCharacterEncoding(CharsetUtil.UTF_8);
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.getWriter().write(JSON.toJSONString(responseResult));
    }
}
