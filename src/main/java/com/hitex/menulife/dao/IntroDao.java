/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hitex.menulife.dao;

import com.hitex.menulife.config.MySqlDao;
import com.hitex.menulife.model.Intro;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author lkintheend
 */
public class IntroDao {

    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    public IntroDao() {

    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = MySqlDao.getInstance().getConnection();
        return conn;
    }

    public Intro getIntro() {

        Connection connection = null;
        Intro intro = new Intro();
        try {
            String queryString = "SELECT * FROM abouts";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                intro.setId(resultSet.getString("id"));
                intro.setContent(resultSet.getString("content"));
                intro.setCreatedAt(resultSet.getString("created_at"));
                intro.setUpdatedAt(resultSet.getString("updated_at"));
            }
            return intro;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (ptmt != null) {
                    ptmt.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return intro;
    }
}
