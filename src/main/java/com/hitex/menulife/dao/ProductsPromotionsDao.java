/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hitex.menulife.dao;

import com.hitex.menulife.config.MySqlDao;
import com.hitex.menulife.model.ProductsPromotions;
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
public class ProductsPromotionsDao {

    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    public ProductsPromotionsDao() {

    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = MySqlDao.getInstance().getConnection();
        return conn;
    }

    public ArrayList<ProductsPromotions> getAllProductssPromotions(int offset, int limit) {
        Connection connection = null;
        ArrayList<ProductsPromotions> listProductsPromotions = new ArrayList<>();
        try {
            String queryString = "SELECT * from products_promotions limit ? offset ?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, limit);
            ptmt.setInt(2, offset);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                ProductsPromotions p = new ProductsPromotions();
                p.setId(resultSet.getString("id"));
                p.setName(resultSet.getString("name"));
                p.setSlugName(resultSet.getString("slug_name"));
                p.setDescription(resultSet.getString("description"));
                p.setContent(resultSet.getString("content"));
                p.setPrice(resultSet.getString("price"));
                p.setQuantityGift(resultSet.getString("quantity_gift"));
                p.setDateTimeFrom(Util.convertStringToTimestamp(resultSet.getString("datetime_from")));
                p.setDateTimeTo(Util.convertStringToTimestamp(resultSet.getString("datetime_to")));
                p.setQuantityReceiver(resultSet.getString("quantity_receiver"));
                p.setImage(resultSet.getString("image"));
                p.setStatus(resultSet.getString("status"));
                p.setUserCreatedId(resultSet.getString("user_created_id"));
                p.setCreatedAt(resultSet.getString("created_at")==null?"":Util.convertStringToTimestamp(resultSet.getString("created_at")));
                p.setUpdatedAt(resultSet.getString("updated_at")==null?"":Util.convertStringToTimestamp(resultSet.getString("updated_at")));

                listProductsPromotions.add(p);
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
        return listProductsPromotions;
    }

    public ArrayList<ProductsPromotions> getTopProductssPromotions() {
        Connection connection = null;
        ArrayList<ProductsPromotions> listProductsPromotions = new ArrayList<>();
        try {
            String queryString = "SELECT * from products_promotions";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);

            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                ProductsPromotions p = new ProductsPromotions();
                p.setId(resultSet.getString("id"));
                p.setName(resultSet.getString("name"));
                p.setSlugName(resultSet.getString("slug_name"));
                p.setDescription(resultSet.getString("description"));
                p.setContent(resultSet.getString("content"));
                p.setPrice(resultSet.getString("price"));
                p.setQuantityGift(resultSet.getString("quantity_gift"));
                p.setDateTimeFrom(resultSet.getString("datetime_from")==null?"":Util.convertStringToTimestamp(resultSet.getString("datetime_from")));
                p.setDateTimeTo(resultSet.getString("datetime_to")==null?"":Util.convertStringToTimestamp(resultSet.getString("datetime_to")));
                p.setQuantityReceiver(resultSet.getString("quantity_receiver"));
                p.setImage(resultSet.getString("image"));
                p.setStatus(resultSet.getString("status"));
                p.setUserCreatedId(resultSet.getString("user_created_id"));
                p.setCreatedAt(resultSet.getString("created_at")==null?"":Util.convertStringToTimestamp(resultSet.getString("created_at")));
                p.setUpdatedAt(resultSet.getString("updated_at")==null?"":Util.convertStringToTimestamp(resultSet.getString("updated_at")));

                listProductsPromotions.add(p);
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
        return listProductsPromotions;
    }
    
    public ProductsPromotions getProductsPromotionsById(int id) {
        Connection connection = null;
        ProductsPromotions p = new ProductsPromotions();
        try {
            String queryString = "SELECT * from products_promotions where id = ?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, String.valueOf(id));
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                p.setId(resultSet.getString("id"));
                p.setName(resultSet.getString("name"));
                p.setSlugName(resultSet.getString("slug_name"));
                p.setDescription(resultSet.getString("description"));
                p.setContent(resultSet.getString("content"));
                p.setPrice(resultSet.getString("price"));
                p.setQuantityGift(resultSet.getString("quantity_gift"));
                p.setDateTimeFrom(resultSet.getString("datetime_from"));
                p.setDateTimeTo(resultSet.getString("datetime_to"));
                p.setQuantityReceiver(resultSet.getString("quantity_receiver"));
                p.setImage(resultSet.getString("image"));
                p.setStatus(resultSet.getString("status"));
                p.setUserCreatedId(resultSet.getString("user_created_id"));
                
                
                p.setCreatedAt(resultSet.getString("created_at")==null?"":Util.convertStringToTimestamp(resultSet.getString("created_at")));
                p.setUpdatedAt(resultSet.getString("updated_at")==null?"":Util.convertStringToTimestamp(resultSet.getString("updated_at")));
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
        String tableCount = "products_promotions";
        
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
        ProductsPromotionsDao pd = new ProductsPromotionsDao();
        ArrayList<ProductsPromotions> lp = pd.getAllProductssPromotions(3, 3);
        for (ProductsPromotions product : lp) {
            System.out.println(product);
        }
        System.out.println(pd.getProductsPromotionsById(16));
    }
}
