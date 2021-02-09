package com.panghu.common;

import com.panghu.exception.AgentException;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author: panghu
 * @Description:
 * @Date: Created in 9:47 2021/1/29
 * @Modified By:
 */
@Data
@Accessors(chain = true)
public class ResultDTO<T> {
    private String code;
    private String message;
    private T data;
    private Boolean success;

    public ResultDTO() {
        this.code = "200";
        this.message = "操作成功";
        this.success = true;
    }


    public ResultDTO(String code,String message,boolean success){
        this.code = code;
        this.success = success;
        this.message = message;
    }

    public ResultDTO(T data) {
        this.code="200";
        this.message="success";
        this.data = data;
        this.success = true;
    }

    private ResultDTO(ErrorTypeEnum codeMsg) {
        if (codeMsg==null) {
            return;
        }
        this.code = codeMsg.getCode();
        this.message = codeMsg.getMsg();
    }


    public static <T> ResultDTO<T> successData(T data){
        return new ResultDTO<T>(data);
    }

    public static <T> ResultDTO<T> error(ErrorTypeEnum codeMsg){
        return new ResultDTO<>(codeMsg);
    }

    public static <T> ResultDTO<T> error(AgentException exception){
        return new ResultDTO<T>(exception.getCode(),exception.getMessage(),false);
    }


    public static  ResultDTO<String> error(String code,String msg){
        return new ResultDTO<>(code,msg,false);
    }
}

