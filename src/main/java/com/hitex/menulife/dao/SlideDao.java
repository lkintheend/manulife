/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hitex.menulife.dao;

import com.hitex.menulife.config.MySqlDao;
import com.hitex.menulife.config.StringConfig;
import com.hitex.menulife.model.ProductsPromotions;
import com.hitex.menulife.model.Slide;
import com.hitex.menulife.util.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author lkintheend
 */
public class SlideDao {

    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = MySqlDao.getInstance().getConnection();
        return conn;
    }

    public SlideDao() {
    }

    public ArrayList<Slide> getListSlides() {
        Connection connection = null;
        ArrayList<Slide> listSlide = new ArrayList<>();
        try {
            String queryString = "SELECT * from slides";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);

            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                Slide slide = new Slide();
                slide.setId(resultSet.getString("id"));
                slide.setContent(resultSet.getString("content"));
                slide.setImage(resultSet.getString("image"));
                slide.setLink(resultSet.getString("link"));
                listSlide.add(slide);
            }
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
            } catch (Exception e) {
            }

        }
        return listSlide;
    }
    
    public static void main(String[] args) {
        SlideDao slideDao = new SlideDao();
        System.out.println(slideDao.getListSlides());
    }
}
