package com.panghu.service.sql;

import com.panghu.common.ErrorTypeEnum;
import com.panghu.common.SqlScriptEnum;
import com.panghu.exception.AgentException;
import com.panghu.manager.sql.ConnectionManager;
import lombok.extern.slf4j.Slf4j;

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
public class SqlExecutorService {

    private ConnectionManager connectionManager = new ConnectionManager();

    private boolean executeScript(String script) {
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
            return statement.execute(script);
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
    public boolean  createDatabase(String dbName) {
        String script = String.format(SqlScriptEnum.CREATE_DATABASE, dbName);
        return executeScript(script);
    }

    /**
     * 使用库
     * @param dbName
     * @return
     */
    public boolean useDatabase(String dbName) {
        String script = String.format(SqlScriptEnum.USE_DATABASE, dbName);
        return executeScript(script);
    }

}
