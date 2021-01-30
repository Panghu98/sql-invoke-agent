package com.panghu.manager.sql;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author: panghu
 * @Description:
 * @Date: Created in 11:10 2021/1/12
 * @Modified By:
 */
@Slf4j
public class ConnectionManager {

    private final String newUrl;
    private String username;
    private String password;

    public static ConnectionManager newInstance(String host,String username,String password,String database) {
        return new ConnectionManager(host, username, password,database);
    }


    private ConnectionManager(String host,String username,String password,String database) {
        this.newUrl = "jdbc:mysql://" + host +":3306/" + database;
        this.username = username;
        this.password = password;
    }

    /**
     * 获取连接
     * @return
     */
    public Connection getConnection() {
        Connection conn = null;
        try {
            String mysqlDriver = "com.mysql.jdbc.Driver";

            Class.forName(mysqlDriver);
            conn = DriverManager.getConnection(newUrl, username,  password);
        }catch (Exception e) {
            log.error(e.getMessage());
        }

        return conn;
    }

}
