/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hitex.menulife.dao;

import com.hitex.menulife.config.MySqlDao;
import com.hitex.menulife.model.Product;
import com.hitex.menulife.model.MenuProducts;
import com.hitex.menulife.model.ProductsGroup;
import com.hitex.menulife.model.ProductsGroupExtends;
import com.hitex.menulife.util.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author lkintheend
 */
public class MenuDao {

    PreparedStatement ptmt = null;
    PreparedStatement ptmt2 = null;
    ResultSet resultSet = null;
    ResultSet resultSet2 = null;

    public MenuDao() {

    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = MySqlDao.getInstance().getConnection();
        return conn;
    }

    public ArrayList<ProductsGroupExtends> getMenu() {

        Connection connection = null;
        ArrayList<ProductsGroupExtends> listMenu = new ArrayList<>();
        try {
            String queryString = "select * from products_group";
            System.out.println("queryString = " + queryString);
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            ProductsGroupExtends productsGroupExtends;
            while (resultSet.next()) {
                productsGroupExtends = new ProductsGroupExtends();

                productsGroupExtends.setId(resultSet.getString("id"));
                productsGroupExtends.setName(resultSet.getString("name"));
                productsGroupExtends.setSlugName(resultSet.getString("slug_name"));
                productsGroupExtends.setStartus(resultSet.getString("status"));



                queryString = "select * from products where products_group_id= ?";
                ptmt2 = connection.prepareStatement(queryString);
                ptmt2.setString(1, resultSet.getString("id"));

                System.out.println(ptmt2.toString());
                resultSet2 = ptmt2.executeQuery();
                
                ArrayList<Product> listProducts = new ArrayList<>();
                
                while (resultSet2.next()) {
                    Product p = new Product();
                    p.setId(resultSet2.getString("id"));
                    p.setName(resultSet2.getString("name"));
                    p.setSlugName(resultSet2.getString("slug_name"));
                    p.setImg(resultSet2.getString("image"));
                    p.setDescription(resultSet2.getString("description"));

                    listProducts.add(p);
                }
                
                productsGroupExtends.setListProducts(listProducts);
                listMenu.add(productsGroupExtends);
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
        return listMenu;
    }
//    public ArrayList<MenuProducts> getMenu() {
//
//        Connection connection = null;
//        ArrayList<MenuProducts> listMenu = new ArrayList<>();
//        try {
//            String queryString = "select * from products_group";
//            System.out.println("queryString = " + queryString);
//            connection = getConnection();
//            ptmt = connection.prepareStatement(queryString);
//            resultSet = ptmt.executeQuery();
//            MenuProducts menuProducts;
//            while (resultSet.next()) {
//                menuProducts = new MenuProducts();
//
//                ProductsGroup pg = new ProductsGroup();
//                pg.setId(resultSet.getString("id"));
//                pg.setName(resultSet.getString("name"));
//                pg.setSlugName(resultSet.getString("slug_name"));
//                pg.setStartus(resultSet.getString("status"));
//
//                menuProducts.setProductsGroup(pg);
//
//                queryString = "select * from products where products_group_id= ?";
//                ptmt2 = connection.prepareStatement(queryString);
//                ptmt2.setString(1, resultSet.getString("id"));
//
//                System.out.println(ptmt2.toString());
//                resultSet2 = ptmt2.executeQuery();
//                
//                ArrayList<Product> listProducts = new ArrayList<>();
//                
//                while (resultSet2.next()) {
//                    Product p = new Product();
//                    p.setId(resultSet2.getString("id"));
//                    p.setName(resultSet2.getString("name"));
//                    p.setSlugName(resultSet2.getString("slug_name"));
//                    p.setImg(resultSet2.getString("image"));
//                    p.setDescription(resultSet2.getString("description"));
//
//                    listProducts.add(p);
//                }
//                
//                menuProducts.setListProducts(listProducts);
//                listMenu.add(menuProducts);
//            }
//            
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (resultSet != null) {
//                    resultSet.close();
//                }
//                if (ptmt != null) {
//                    ptmt.close();
//                }
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        }
//        return listMenu;
//    }

    public static void main(String[] args) {
        MenuDao menu = new MenuDao();

        System.out.println(menu.getMenu().get(1));
    }

}
