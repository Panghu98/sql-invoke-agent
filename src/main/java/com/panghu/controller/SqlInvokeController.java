package com.panghu.controller;

import com.panghu.common.ResultDTO;
import com.panghu.controller.param.docker.SqlExecuteParam;
import com.panghu.service.sql.SqlExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: panghu
 * @Description:
 * @Date: Created in 16:23 2021/1/29
 * @Modified By:
 */
@RequestMapping("/sql")
@RestController
public class SqlInvokeController {

    @Autowired
    private SqlExecutorService sqlExecutorService;

    /**
     * 一般来说数据库的创建都是需要提前完成的
     * @param param
     * @return
     */
    @RequestMapping( value = "/execute",method = RequestMethod.POST)
    public ResultDTO<Void> executeSql(@RequestBody SqlExecuteParam param) {
        String host = param.getHost();
        String username = param.getUsername();
        String password = param.getPassword();
        String sqlScript = param.getSqlScript();
        String dbName = param.getDbName();
        return sqlExecutorService.executeScript(host,username,password,dbName,sqlScript);
    }

}
