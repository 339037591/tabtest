package com.aas.config;


import com.aas.controller.base.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class InterfaceExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(InterfaceExceptionHandler.class);

    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Result runtimeException(RuntimeException e) {
        log.error("failed-error", e.fillInStackTrace());
        if("403".equals(e.getMessage())){
            return Result.errro(403,"无效token");
        }
        return Result.errro(500,e.toString());
    }
}
