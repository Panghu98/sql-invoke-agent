package com.panghu.service.sql;

import com.panghu.common.ErrorTypeEnum;
import com.panghu.common.ResultDTO;
import com.panghu.common.SqlScriptEnum;
import com.panghu.exception.AgentException;
import com.panghu.manager.sql.ConnectionManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author: panghu
 * @Description:
 * @Date: Created in 11:21 2021/1/12
 * @Modified By:
 */
@Slf4j
@Service
public class SqlExecutorService {

    private void doExecuteScript(String host,String username,String password,String script) {

        ConnectionManager connectionManager = ConnectionManager.newInstance(host, username, password);

        //获取连接
        Connection connection = connectionManager.getConnection();

        // 校验连接
        if (connection == null) {
            throw new AgentException(ErrorTypeEnum.DB_CONNECTION_ERROR);
        }

        Statement statement = null;
        try {
            statement = connection.createStatement();
            log.info("start invoke script :" + script);
            statement.execute(script);
        } catch (SQLException sqlException) {
            log.error("Sql script invoke error..");
            log.error(sqlException.getMessage());
            throw new AgentException(ErrorTypeEnum.SQL_SCRIPT_EXECUTE_ERROR);
        }finally {
            if(statement != null) {
                try {
                    statement.close();
                    connection.close();
                } catch (SQLException sqlException) {
                    log.error("sql executor tool close error" + sqlException.getMessage());
                }
            }
        }
    }

    /**
     * 建库
     * @param dbName
     * @return
     */
    public void  createDatabase(String host,String username,String password,String dbName) {
        String script = String.format(SqlScriptEnum.CREATE_DATABASE, dbName);
        doExecuteScript(host, username, password, script);
    }

    /**
     * 使用库
     * @param dbName
     * @return
     */
    public ResultDTO<Void> executeScript(String host, String username, String password, String dbName, String script) {
        script = String.format(SqlScriptEnum.USE_DATABASE, dbName) + script;
        doExecuteScript(host, username, password, script);

        return ResultDTO.successData(null);
    }


}
