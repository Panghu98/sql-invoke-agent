package com.panghu.exception;

import com.panghu.common.ErrorTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author: panghu
 * @Description:
 * @Date: Created in 11:28 2021/1/12
 * @Modified By:
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AgentException extends RuntimeException{

    private String code;

    private String message;

    public AgentException(ErrorTypeEnum errorTypeEnum) {
        super();
        this.code = errorTypeEnum.getCode();
        this.message = errorTypeEnum.getMsg();
    }

    public AgentException(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

}
