package com.panghu.common;

import lombok.Getter;

/**
 * @author: panghu
 * @Description:
 * @Date: Created in 11:33 2021/1/12
 * @Modified By:
 */
@Getter
public enum ErrorTypeEnum {
    /**
     *
     */
    /*    系统异常   */
    DB_CONNECTION_ERROR("1","DB连接错误"),
    SQL_SCRIPT_EXECUTE_ERROR("2","SQL脚本执行错误");


    private String code;

    private String msg;

    ErrorTypeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
