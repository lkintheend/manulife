/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hitex.menulife.dao;

import com.hitex.menulife.config.MySqlDao;
import com.hitex.menulife.model.News;
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
public class NewsDao {

    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    public NewsDao() {

    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = MySqlDao.getInstance().getConnection();
        return conn;
    }

    public ArrayList<News> getAllNews(int offset, int limit) {
        Connection connection = null;
        ArrayList<News> listNews = new ArrayList<>();
        try {
            String queryString = "SELECT * from news limit ? offset ?";
            System.out.println("queryString = " + queryString);
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, limit);
            ptmt.setInt(2, offset);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                News news = new News();
                news.setId(resultSet.getString("id"));
                news.setTitle(resultSet.getString("title"));
                news.setSlugName(resultSet.getString("slug_name"));
                news.setDescription(resultSet.getString("description"));
                news.setImage(resultSet.getString("image"));
                news.setCreatedAt(Util.convertStringToTimestamp(resultSet.getString("created_at")));
                news.setUpdatedAt(Util.convertStringToTimestamp(resultSet.getString("updated_at")));
                listNews.add(news);
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
        return listNews;
    }

    public News getNewsById(int id) {
        Connection connection = null;
        News news = new News();
        try {
            String queryString = "SELECT * from news where id = ?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, String.valueOf(id));
            resultSet = ptmt.executeQuery();

            while (resultSet.next()) {
                news.setId(resultSet.getString("id"));
                news.setTitle(resultSet.getString("title"));
                news.setSlugName(resultSet.getString("slug_name"));
                news.setDescription(resultSet.getString("description"));
                news.setContent(resultSet.getString("content"));
                news.setImage(resultSet.getString("image"));
                news.setStatus(resultSet.getString("status"));
                news.setUserCreatedId(resultSet.getString("user_created_id"));
                news.setCreatedAt(Util.convertStringToTimestamp(resultSet.getString("created_at")));
                news.setUpdatedAt(Util.convertStringToTimestamp(resultSet.getString("updated_at")));
            }

            return news;

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
        return news;
    }

    public int countRow() {
        Connection connection = null;
        String tableCount = "news";

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

    public News getIntro() {
        Connection connection = null;
        News news = new News();
        try {
            String queryString = "SELECT * from news where status = 4";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            while (resultSet != null && resultSet.next()) {
                news.setId(             resultSet.getString("id"));
                news.setIdGroupNews(    resultSet.getString("id_group_new") == null     ? "" : resultSet.getString("id_group_new"));
                news.setTitle(          resultSet.getString("title") == null            ? "" : resultSet.getString("title"));
                news.setSlugName(       resultSet.getString("slug_name") == null        ? "" : resultSet.getString("slug_name"));
                news.setDescription(    resultSet.getString("description") != null      ? "" : resultSet.getString("description"));
                news.setContent(        resultSet.getString("content") == null          ? "" : resultSet.getString("content"));
                news.setImage(          resultSet.getString("image") == null            ? "" : resultSet.getString("image"));
                news.setUserCreatedId(  resultSet.getString("user_created_id") == null  ? "" : resultSet.getString("user_created_id"));
                news.setStatus(         resultSet.getString("status") == null           ? "" : resultSet.getString("status"));
                news.setCreatedAt(      resultSet.getString("created_at") == null       ? "" : resultSet.getString("created_at"));
                news.setUpdatedAt(      resultSet.getString("updated_at") == null       ? "" : resultSet.getString("updated_at"));
            }
            return news;
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
        return news;
    }
    
    public News getCustomerCare() {
        Connection connection = null;
        News news = new News();
        try {
            String queryString = "SELECT * from news where status = 5";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            while (resultSet != null && resultSet.next()) {
                news.setId(             resultSet.getString("id"));
                news.setIdGroupNews(    resultSet.getString("id_group_new") == null     ? "" : resultSet.getString("id_group_new"));
                news.setTitle(          resultSet.getString("title") == null            ? "" : resultSet.getString("title"));
                news.setSlugName(       resultSet.getString("slug_name") == null        ? "" : resultSet.getString("slug_name"));
                news.setDescription(    resultSet.getString("description") != null      ? "" : resultSet.getString("description"));
                news.setContent(        resultSet.getString("content") == null          ? "" : resultSet.getString("content"));
                news.setImage(          resultSet.getString("image") == null            ? "" : resultSet.getString("image"));
                news.setUserCreatedId(  resultSet.getString("user_created_id") == null  ? "" : resultSet.getString("user_created_id"));
                news.setStatus(         resultSet.getString("status") == null           ? "" : resultSet.getString("status"));
                news.setCreatedAt(      resultSet.getString("created_at") == null       ? "" : resultSet.getString("created_at"));
                news.setUpdatedAt(      resultSet.getString("updated_at") == null       ? "" : resultSet.getString("updated_at"));
            }
            return news;
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
        return news;
    }

    public static void main(String[] args) {
        NewsDao nd = new NewsDao();
        ArrayList<News> ln = nd.getAllNews(1, 2);
        for (News news : ln) {
            System.out.println(news);
        }
//        System.out.println(nd.getNewsById(7));
    }
}
