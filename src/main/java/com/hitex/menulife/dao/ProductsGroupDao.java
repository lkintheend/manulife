/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hitex.menulife.dao;

import com.hitex.menulife.config.MySqlDao;
import com.hitex.menulife.model.Product;
import com.hitex.menulife.model.ProductsGroup;
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
public class ProductsGroupDao {

    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    public ProductsGroupDao() {

    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = MySqlDao.getInstance().getConnection();
        return conn;
    }

    public ArrayList<ProductsGroup> getAllProductsGroup(int offset, int limit) {
        Connection connection = null;
        ArrayList<ProductsGroup> listProductsGroups = new ArrayList<>();
        try {
            String queryString = "SELECT * from products_group limit ? offset ?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, limit);
            ptmt.setInt(2, offset);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                ProductsGroup p = new ProductsGroup();
                p.setId(resultSet.getString("id"));
                p.setName(resultSet.getString("name"));
                p.setSlugName(resultSet.getString("slug_name"));
                p.setStartus(resultSet.getString("status"));
                p.setCreatedAt(Util.convertStringToTimestamp(resultSet.getString("created_at")));
                p.setUpdatedAt(Util.convertStringToTimestamp(resultSet.getString("updated_at")));
                listProductsGroups.add(p);
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
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return listProductsGroups;
    }

    public ProductsGroup getProductsGroupById(int id) {
        Connection connection = null;
        ProductsGroup p = new ProductsGroup();
        try {
            String queryString = "SELECT * from products_group where id = ?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, String.valueOf(id));
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                p.setId(resultSet.getString("id"));
                p.setName(resultSet.getString("name"));
                p.setSlugName(resultSet.getString("slug_name"));
                p.setStartus(resultSet.getString("status"));
                p.setCreatedAt(Util.convertStringToTimestamp(resultSet.getString("created_at")));
                p.setUpdatedAt(Util.convertStringToTimestamp(resultSet.getString("updated_at")));
            }
            return p;

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
        return p;
    }

        public int countRow() {
        Connection connection = null;
        String tableCount = "products_group";
        
        try {
            String queryString = "SELECT COUNT(id) FROM "+tableCount;
            System.out.println(queryString);
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            if(resultSet!=null&&resultSet.next()){
                return resultSet.getInt(1);
            }
            return 0;
            
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
        return 0;
    }
    
    public static void main(String[] args) {
        ProductsGroupDao pd = new ProductsGroupDao();
        ArrayList<ProductsGroup> lp = pd.getAllProductsGroup(1,10);
        for (ProductsGroup product : lp) {
            System.out.println(product);
        }
    }
}
