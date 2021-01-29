package com.panghu.common;

import com.panghu.exception.AgentException;
import lombok.Data;

/**
 * @author: panghu
 * @Description:
 * @Date: Created in 9:47 2021/1/29
 * @Modified By:
 */
@Data
public class ResultDTO<T> {
    private String code;
    private String message;
    private T data;

    public ResultDTO() {
        this.code = "200";
        this.message = "操作成功";
    }

    /**
     * 用于错误处理
     * @param code 错误码 与 CodeMsg对应
     * @param message 错误提示信息
     */
    public ResultDTO(String code,String message){
        this.code = code;
        this.message = message;
    }

    public ResultDTO(T data) {
        this.code="200";
        this.message="success";
        this.data = data;
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
        return new ResultDTO<T>(exception.getCode(),exception.getMessage());
    }


    public static  ResultDTO<String> error(String code,String msg){
        return new ResultDTO<>(code,msg);
    }
}
