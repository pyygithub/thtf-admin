package com.thtf.common.core.exception.handler;


import com.google.common.collect.ImmutableMap;
import com.thtf.common.core.exception.BusinessException;
import com.thtf.common.core.response.CommonCode;
import com.thtf.common.core.response.ResponseCode;
import com.thtf.common.core.response.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * ---------------------------
 * 全局异常处理类
 * ---------------------------
 * 作者：  pyy
 * 时间：  2019/12/26 17:43
 * 版本：  v1.0
 * ---------------------------
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    // 使用EXCEPTIONS存放异常类型和错误代码的映射，ImmutableMap的特点的一旦创建不可改变，并且线程安全
    private static ImmutableMap<Class<? extends Throwable>, ResponseCode> EXCEPTIONS;

    // 使用builder来构建一个异常类型和错误代码的异常
    protected static ImmutableMap.Builder<Class<? extends Throwable>,ResponseCode> builder = ImmutableMap.builder();

    static{
        // 在这里加入一些基础的异常类型判断
        builder.put(HttpMessageNotReadableException.class, CommonCode.INVALID_PARAM);
        builder.put(HttpRequestMethodNotSupportedException.class, CommonCode.INVALID_REQUEST_METHOD);
    }

    /**
     * 捕获 Exception异常
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseResult customException(RuntimeException e) {
        log.error("【RuntimeException】", e);
        if (EXCEPTIONS == null) {
            EXCEPTIONS = builder.build();
        }
        final ResponseCode responseCode = EXCEPTIONS.get(e.getClass());
        final ResponseResult responseResult;
        if (responseCode != null) {
            responseResult = new ResponseResult(responseCode);
        } else {
            responseResult = new ResponseResult(CommonCode.SERVER_ERROR);
        }
        return responseResult;
    }

    /**
     * 参数错误异常
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult handleException(MethodArgumentNotValidException e) {
        log.error("【MethodArgumentNotValidException】", e);
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
        return new ResponseResult(400, objectError.getDefaultMessage());
    }

    /**
     * 捕获 BusinessException业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseResult customException(BusinessException e) {
        log.error("【BusinessException】", e);
        return new ResponseResult(e.getResponseCode());
    }
}
