package com.ruida.springbootdemo.aop;

import com.ruida.springbootdemo.beans.CommonResult;
import com.ruida.springbootdemo.exception.BizException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @description: 全局异常处理
 * @author: chenjy
 * @create: 2020-04-03 09:01
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BizException.class)
    @ResponseBody
    public CommonResult bizExceptionHandler(BizException e){
        CommonResult commonResult = new CommonResult(e.getErrorCode(),e.getErrorMsg());
        return commonResult;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CommonResult exceptionHandler(Exception e){
        CommonResult commonResult = new CommonResult(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),"操作失败");
        return commonResult;
    }

}
