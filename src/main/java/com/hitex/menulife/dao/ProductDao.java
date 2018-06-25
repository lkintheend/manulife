/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hitex.menulife.dao;

import com.hitex.menulife.config.MySqlDao;
import com.hitex.menulife.model.Product;
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
public class ProductDao {

    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    public ProductDao() {

    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = MySqlDao.getInstance().getConnection();
        return conn;
    }

    public ArrayList<Product> getAllProducts(int offset, int limit) {
        Connection connection = null;
        ArrayList<Product> listProducts = new ArrayList<>();
        try {
            String queryString = "SELECT * from products limit ? offset ?";
            System.out.println("queryString = " + queryString);
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, limit);
            ptmt.setInt(2, offset);
            System.out.println(ptmt.toString());
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                Product p = new Product();
                p.setId(resultSet.getString("id"));
                p.setName(resultSet.getString("name"));
                p.setSlugName(resultSet.getString("slug_name"));
                p.setImg(resultSet.getString("image"));
                p.setDescription(resultSet.getString("description"));
                p.setUpdatedAt(Util.convertStringToTimestamp(resultSet.getString("updated_at")));
                listProducts.add(p);
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
        return listProducts;
    }

    public ArrayList<Product> getTopProducts() {
        Connection connection = null;
        ArrayList<Product> listProducts = new ArrayList<>();
        try {
            String queryString = "SELECT * from products where highlights = 1";
            System.out.println("queryString = " + queryString);
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);

            System.out.println(ptmt.toString());
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                Product p = new Product();
                p.setId(resultSet.getString("id"));
                p.setName(resultSet.getString("name"));
                p.setSlugName(resultSet.getString("slug_name"));
                p.setImg(resultSet.getString("image"));
                p.setDescription(resultSet.getString("description"));
                p.setUpdatedAt(Util.convertStringToTimestamp(resultSet.getString("updated_at")));
                listProducts.add(p);
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
        return listProducts;
    }

    public Product getProductById(int id) {
        Connection connection = null;
        Product p = new Product();
        try {
            String queryString = "SELECT * from products where id = ?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, String.valueOf(id));
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                p.setId(resultSet.getString("id"));
                p.setName(resultSet.getString("name"));
                p.setSlugName(resultSet.getString("slug_name"));
                p.setDescription(resultSet.getString("description"));
                p.setImg(resultSet.getString("image"));
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
        String tableCount = "products";

        try {
            String queryString = "SELECT COUNT(id) FROM " + tableCount;
            System.out.println(queryString);
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            if (resultSet != null && resultSet.next()) {
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

    public Product getIntro() {
        Connection connection = null;
        Product p = new Product();
        try {
            String queryString = "SELECT * from products where status = 4";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                p.setId(resultSet.getString("id"));
                p.setName(resultSet.getString("name"));
                p.setSlugName(resultSet.getString("slug_name"));
                p.setDescription(resultSet.getString("description"));
                p.setImg(resultSet.getString("image"));
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

    public static void main(String[] args) {
        ProductDao pd = new ProductDao();
//        ArrayList<Product> lp = pd.getAllProducts(2, 5);
//        for (Product product : lp) {
//            System.out.println(product);
//        }
//        System.out.println(pd.getProductById(12));
        System.out.println(pd.getIntro());
    }
}
