/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hitex.menulife.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */
public class MySqlDao {



    String driverClassName = "com.mysql.jdbc.Driver";
    String connectionUrl = "jdbc:mysql://123.31.41.31:3306/manulife?useUnicode=true&characterEncoding=utf-8";
    String dbUser = "manulife";
    String dbPwd = "12manulifepass";
    

    private static MySqlDao ConnectionMySql = null;

    private MySqlDao() {
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection(connectionUrl, dbUser, dbPwd);
        return conn;
    }

    public static MySqlDao getInstance() {
        if (ConnectionMySql == null) {
            ConnectionMySql = new MySqlDao();
        }
        return ConnectionMySql;
    }
    
    public static void main(String[] args) {
        try {
            Connection connection = ConnectionMySql.getInstance().getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(MySqlDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


