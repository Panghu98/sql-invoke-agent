package com.panghu.aspect;

/**
 * @author: panghu
 * @Description:
 * @Date: Created in 9:45 2021/1/29
 * @Modified By:
 */
import com.panghu.common.ResultDTO;
import com.panghu.exception.AgentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Objects;

/**
 * @author project
 * @title: GlobalExceptionHandler
 * @projectName oil-supply-chain
 * @date 19-4-7 下午5:28
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    private final static String EXCEPTION_MSG_KEY = "Exception message : ";

    @ResponseBody
    @ExceptionHandler(AgentException.class)
    public ResultDTO handleSelfException(AgentException exception){
        log.error(EXCEPTION_MSG_KEY+exception.getMessage());
        return ResultDTO.error(exception);
    }

    @ResponseBody
    @ExceptionHandler(NullPointerException.class)
    public void handleNullPointException(AgentException exception){
        log.error(EXCEPTION_MSG_KEY+exception.getMessage());
    }


    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResultDTO handleValidException(MethodArgumentNotValidException e){
        log.error(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage());
        return ResultDTO.error("103",e.getBindingResult().getFieldError().getDefaultMessage());
    }
}