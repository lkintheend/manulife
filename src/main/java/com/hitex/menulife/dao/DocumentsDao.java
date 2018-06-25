/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hitex.menulife.dao;

import com.hitex.menulife.config.MySqlDao;
import com.hitex.menulife.model.Documents;
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
public class DocumentsDao {

    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    public DocumentsDao() {

    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = MySqlDao.getInstance().getConnection();
        return conn;
    }

    public ArrayList<Documents> getAllDocuments(int offset, int limit) {
        Connection connection = null;
        ArrayList<Documents> listDocuments = new ArrayList<>();
        try {
            String queryString = "SELECT * from documents limit ? offset ?";
            System.out.println("queryString = " + queryString);
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setInt(1, limit);
            ptmt.setInt(2, offset);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                Documents documents = new Documents();
                documents.setId(resultSet.getString("id"));
                documents.setName(resultSet.getString("name"));
                documents.setLink(resultSet.getString("link"));
                documents.setStatus(resultSet.getString("status"));
                documents.setCreatedAt(Util.convertStringToTimestamp(resultSet.getString("created_at")));
                documents.setUpdatedAt(Util.convertStringToTimestamp(resultSet.getString("updated_at")));
                listDocuments.add(documents);
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
        return listDocuments;
    }

    public Documents getDocumentsById(int id) {
        Connection connection = null;
        Documents documents = new Documents();
        try {
            String queryString = "SELECT * from documents where id = ?";
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, String.valueOf(id));
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                documents.setId(resultSet.getString("id"));
                documents.setName(resultSet.getString("name"));
                documents.setLink(resultSet.getString("link"));
                documents.setStatus(resultSet.getString("status"));
                documents.setCreatedAt(Util.convertStringToTimestamp(resultSet.getString("created_at")));
                documents.setUpdatedAt(Util.convertStringToTimestamp(resultSet.getString("updated_at")));
            }
            return documents;
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
        return documents;
    }

    
    //ham dem so dong cua bang trong db
    public int countRow() {
        Connection connection = null;
        String tableCount = "documents";
        
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
        DocumentsDao pd = new DocumentsDao();
//        ArrayList<Documents> lp = pd.getAllDocuments();
//        for (Documents documents : lp) {
//            System.out.println(documents);
//        }
//        System.out.println(pd.getDocumentsById(1));
        System.out.println(pd.countRow());
    }
}
