/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hitex.menulife.dao;

import com.hitex.menulife.config.MySqlDao;
import com.hitex.menulife.model.Notification;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author lkintheend
 */
public class NotificationDao {

    PreparedStatement ptmt = null;
    ResultSet resultSet = null;

    public NotificationDao() {

    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = MySqlDao.getInstance().getConnection();
        return conn;
    }

    public ArrayList<Notification> getListNotificationByUserId(String userId, int offset, int limit) {

        Connection connection = null;
        ArrayList<Notification> listNotification = new ArrayList<>();
        try {
            String queryString = "SELECT * from notification where id_user = ? LIMIT ? OFFSET ?";
            System.out.println("queryString = " + queryString);
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, userId);
            ptmt.setInt(2, limit);
            ptmt.setInt(3, offset);

            System.out.println(ptmt.toString());

            resultSet = ptmt.executeQuery();

            while (resultSet != null && resultSet.next()) {
                Notification notification = new Notification();
                notification.setId(resultSet.getString("id"));
                notification.setIsRead(resultSet.getString("is_read"));
                notification.setTitle(resultSet.getString("title"));
                notification.setContent(resultSet.getString("content"));
                notification.setStatus(resultSet.getString("status"));
                notification.setCreatedAt(resultSet.getString("created_at"));
                notification.setIdUser(resultSet.getString("id_user"));
                notification.setIdService(resultSet.getString("id_service"));
                notification.setType(resultSet.getString("type"));
                listNotification.add(notification);
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
        return listNotification;

    }

    public int countNotificationUnseenByUserId(String userId) {
        Connection connection = null;

        try {
            String queryString = "SELECT COUNT(id) FROM notification WHERE  is_read = 0 AND id_user = ?";
            System.out.println(queryString);
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, userId);
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

    public Notification getDetailNotificationById(String id) {
        Connection connection = null;
        Notification notification = new Notification();
        try {
            String queryString = "SELECT * from notification where id = ?";
            System.out.println("queryString = " + queryString);
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, id);

            System.out.println(ptmt.toString());

            resultSet = ptmt.executeQuery();

            while (resultSet != null && resultSet.next()) {
                notification = new Notification();
                notification.setId(resultSet.getString("id"));
                notification.setIsRead(resultSet.getString("is_read"));
                notification.setTitle(resultSet.getString("title"));
                notification.setContent(resultSet.getString("content"));
                notification.setStatus(resultSet.getString("status"));
                notification.setCreatedAt(resultSet.getString("created_at"));
                notification.setIdUser(resultSet.getString("id_user"));
                notification.setIdService(resultSet.getString("id_service"));
                notification.setType(resultSet.getString("type"));
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
        return notification;
    }

    public void updateSeenNotification(String id) {
        Connection connection = null;
        try {
            String queryString = "UPDATE notification SET is_read = 1 WHERE id = ?";
            System.out.println("queryString = " + queryString);
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, id);
            System.out.println(ptmt.toString());
            ptmt.execute();
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
    }

    public int countRow() {
        Connection connection = null;
        String tableCount = "notification";

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

    public int countRowById(String userId) {
        Connection connection = null;
        String tableCount = "notification";

        try {
            String queryString = "SELECT COUNT(id) FROM " + tableCount + " WHERE id_user = ?";
            System.out.println(queryString);
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            ptmt.setString(1, userId);
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

    public static void main(String[] args) {
        NotificationDao notificationDao = new NotificationDao();
        System.out.println(notificationDao.countRowById("3"));
    }

}
