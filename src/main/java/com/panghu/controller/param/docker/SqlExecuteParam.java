package com.panghu.controller.param.docker;

import lombok.Data;

/**
 * @author: panghu
 * @Description:
 * @Date: Created in 10:36 2021/1/29
 * @Modified By:
 */
@Data
public class SqlExecuteParam {

    private String host;

    private String username;

    private String password;

    private String dbName;

    private String sqlScript;


}
