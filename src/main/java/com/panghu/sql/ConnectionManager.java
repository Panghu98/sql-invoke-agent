package com.panghu.sql;

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

    private String mysqlDriver = "com.mysql.jdbc.Driver";
    private String newUrl = "jdbc:mysql://localhost:3306/";
    private String username = "root";
    private String password = "745920";


    public ConnectionManager() {

    }

    public ConnectionManager(String newUrl) {
        this.newUrl = newUrl;
    }

    /**
     * 获取连接
     * @return
     */
    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(mysqlDriver);
            conn = DriverManager.getConnection(newUrl, username,  password);
        }catch (Exception e) {
            log.error(e.getMessage());
        }

        return conn;
    }

}
