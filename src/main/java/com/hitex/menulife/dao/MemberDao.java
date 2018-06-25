/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hitex.menulife.dao;

import com.hitex.menulife.config.MySqlDao;
import com.hitex.menulife.model.Member;
import com.hitex.menulife.util.MD5Generator;
import com.hitex.menulife.util.StringEncrypt;
import com.hitex.menulife.util.Util;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus
 */
public class MemberDao {

    PreparedStatement ptmt = null;
    PreparedStatement ptmt2 = null;
    ResultSet resultSet = null;
    ResultSet resultSet2 = null;

    public MemberDao() {

    }

    private Connection getConnection() throws SQLException {
        Connection conn;
        conn = MySqlDao.getInstance().getConnection();
        return conn;
    }

    public ArrayList<String> getNameMembers() {
        Connection connection = null;
        ArrayList<String> lstName = new ArrayList<>();
        try {
            String queryString = "SELECT * from members";
            System.out.println("queryString = " + queryString);
            connection = getConnection();
            ptmt = connection.prepareStatement(queryString);
            resultSet = ptmt.executeQuery();
            while (resultSet.next()) {
                lstName.add(resultSet.getString(3));
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
        return lstName;
    }

    public String insertMember(String name, String fullName, String passWord, String phone, String email) {
        Connection connection = null;
        Random rd = new Random();
        Long rdString = rd.nextLong() + System.currentTimeMillis(); //ham tao String ngau nhien tao rememberToken
        try {
            connection = getConnection();
            String sql = "insert into members(name, full_name,password, phone, email, status, remember_token) values (?, ?, ?, ?, ?, 0, ?) ";
            ptmt = connection.prepareStatement(sql);
            ptmt.setString(1, name);
            ptmt.setString(2, fullName);
            ptmt.setString(3, MD5Generator.md5(passWord));
            ptmt.setString(4, phone);
            ptmt.setString(5, email);
            ptmt.setString(6, MD5Generator.md5(rdString.toString()));
            System.out.println("ptmt = " + ptmt.toString());
            ptmt.execute();
            return MD5Generator.md5(rdString.toString());
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
        return "";
    }

    public boolean isActiveMember(String email) throws SQLException {
        Connection connection = null;
        int result = 0;
        try {
            connection = getConnection();
            String sql = "select status from members where email = ?";
            ptmt = connection.prepareStatement(sql);
            ptmt.setString(1, email);
            System.out.println("ptmt = " + ptmt.toString());
            resultSet = ptmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            resultSet.next();
            result = resultSet.getInt("status");
        } catch (SQLException ex) {
            Logger.getLogger(MemberDao.class.getName()).log(Level.SEVERE, null, ex);
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
        return result == 1 ? true : false;
    }

    public Member validateMember(String email, String passWord) {

        Connection connection = null;
        Member member = null;
        Random rd = new Random();
        try {
            connection = getConnection();
            String sql = "select * from members where email = ? and password = ?";
            ptmt = connection.prepareStatement(sql);
            ptmt.setString(1, email);
            ptmt.setString(2, MD5Generator.md5(passWord));
            System.out.println(ptmt.toString());
            resultSet = ptmt.executeQuery();
            member = new Member();
            Long rdString = rd.nextLong() + System.currentTimeMillis();

            while (resultSet != null && resultSet.next()) {
                member.setId(resultSet.getString("id"));
                member.setName(resultSet.getString("name"));
                member.setFullName(resultSet.getString("full_name"));
                member.setPassWord(resultSet.getString("password"));
                member.setPhone(resultSet.getString("phone"));
                member.setEmail(resultSet.getString("email"));
                member.setAvatar(resultSet.getString("avatar"));
                member.setStatus(resultSet.getString("status"));
                member.setUpdateAt(resultSet.getString("updated_at"));
                member.setCreatedAt(resultSet.getString("created_at"));
                
                sql = "update members set remember_token =? where email = ? and password = ?";
                ptmt2 = connection.prepareStatement(sql);
                String rememberToken= MD5Generator.md5(rdString.toString());
                ptmt2.setString(1, rememberToken);
                ptmt2.setString(2, resultSet.getString("email"));
                ptmt2.setString(3, resultSet.getString("password"));
                
                ptmt2.executeUpdate();
                member.setRememberToken(rememberToken);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MemberDao.class.getName()).log(Level.SEVERE, null, ex);
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
        return member;
    }
//    

    public boolean activeUser(String rememberToken) {
        Connection connection = null;
        String sql = "";

        if (rememberToken.equals("")) {
            return false;
        }

        try {
            connection = getConnection();
//            sql = "select status from members where remember_token = ?";
            sql = "update members set status = '1'  where remember_token= ? and status = '0'";
            ptmt = connection.prepareStatement(sql);
            ptmt.setString(1, rememberToken);
            if (ptmt.executeUpdate() == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MemberDao.class.getName()).log(Level.SEVERE, null, ex);
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
        return false;
    }

    public boolean changePass(String email, String oldPassWord, String newPassWord) {
        Connection connection = null;
        String sql = "";

        if (email.equals("") || oldPassWord.equals("") || newPassWord.equals("")) {
            return false;
        }

        try {
            connection = getConnection();
//            sql = "select status from members where remember_token = ?";
            sql = "update members set password = ?  where email = ? and password = ?";
            ptmt = connection.prepareStatement(sql);
            ptmt.setString(1, MD5Generator.md5(newPassWord));
            ptmt.setString(2, email);
            ptmt.setString(3, MD5Generator.md5(oldPassWord));
            System.out.println(ptmt.toString());
            if (ptmt.executeUpdate() == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MemberDao.class.getName()).log(Level.SEVERE, null, ex);
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
        return false;
    }

    public boolean checkMemberEmail(String email) {
        Connection connection = null;
        String sql;
        try {
            connection = getConnection();
            sql = " select * from members where email = ? and status in (1, 0)";
            ptmt = connection.prepareStatement(sql);
            ptmt.setString(1, email);
            System.out.println(ptmt.toString());
            resultSet = ptmt.executeQuery();
            return resultSet.next();
        } catch (SQLException ex) {
            Logger.getLogger(MemberDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public Member getMemberInfo(String email) {

        Connection connection = null;
        Member member = null;
        try {
            connection = getConnection();
            String sql = "select * from members where email = ?";
            ptmt = connection.prepareStatement(sql);
            ptmt.setString(1, email);
            System.out.println(ptmt.toString());
            resultSet = ptmt.executeQuery();
            member = new Member();
            while (resultSet != null && resultSet.next()) {
                member.setId(resultSet.getString("id"));
                member.setName(resultSet.getString("name")==null?"":resultSet.getString("name"));
                member.setFullName(resultSet.getString("full_name")==null?"":resultSet.getString("full_name"));
                member.setPhone(resultSet.getString("phone")==null?"":resultSet.getString("phone"));
                member.setEmail(resultSet.getString("email")==null?"":resultSet.getString("email"));
                member.setAvatar(resultSet.getString("avatar")==null?"":resultSet.getString("avatar"));
                member.setStatus(resultSet.getString("status")==null?"":resultSet.getString("status"));
                member.setUpdateAt(resultSet.getString("updated_at")==null?"":Util.convertStringToTimestamp(resultSet.getString("updated_at")));
                member.setCreatedAt(resultSet.getString("created_at")==null?"":Util.convertStringToTimestamp(resultSet.getString("created_at")));
                member.setSex(resultSet.getString("sex")==null?"0":resultSet.getString("sex"));
                member.setBirthDay(resultSet.getString("birthday")==null?"":Util.convertStringToTimestamp(resultSet.getString("birthday")));
            }

        } catch (SQLException ex) {
            Logger.getLogger(MemberDao.class.getName()).log(Level.SEVERE, null, ex);
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
        return member;
    }

    public boolean checkSession(String rememberToken) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "select * from members where remember_token = ?";
            ptmt = connection.prepareStatement(sql);
            ptmt.setString(1, rememberToken);
            System.out.println(ptmt.toString());
            resultSet = ptmt.executeQuery();

            while (resultSet != null && resultSet.next()) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(MemberDao.class.getName()).log(Level.SEVERE, null, ex);
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
        return false;
    }

    public boolean forgotPass(String id, String passWord) throws Exception {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "update members set password= ? where id = ?";
            ptmt = connection.prepareStatement(sql);
            ptmt.setString(1, MD5Generator.md5(passWord));
            ptmt.setString(2, id);
            System.out.println(ptmt.toString());
            if (ptmt.executeUpdate() == 1) {
                return true;
            };

        } catch (SQLException ex) {
            Logger.getLogger(MemberDao.class.getName()).log(Level.SEVERE, null, ex);
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
        return false;
    }
    
    /*Lay id tu email de forgot password*/
    public String getIdMember(String email){
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "select id from members where email = ?";
            ptmt = connection.prepareStatement(sql);
            ptmt.setString(1, email);

            System.out.println(ptmt.toString());
            resultSet = ptmt.executeQuery();
            while(resultSet!=null && resultSet.next()){
                return resultSet.getString("id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(MemberDao.class.getName()).log(Level.SEVERE, null, ex);
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
        return "-1";
    }
    
    public boolean updateProfile(String email, String fullName, String sex, String birthday, String phone) {
        Connection connection = null;
        String sql = "";

        try {
            connection = getConnection();
//            sql = "select status from members where remember_token = ?";
            sql = "update members set full_name= ? , sex= ? , birthday = ?, phone = ? where email = ?";
            ptmt = connection.prepareStatement(sql);
            ptmt.setString(1, fullName==null? "default":fullName);
            ptmt.setString(2, sex==null? "0":sex);
            ptmt.setString(3, birthday==null? "1997-11-01":birthday);
            ptmt.setString(4, phone==null? "0":phone);
            ptmt.setString(5, email==null? "default":email);
            System.out.println(ptmt.toString());
            if (ptmt.executeUpdate() == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(MemberDao.class.getName()).log(Level.SEVERE, null, ex);
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
        return false;
    }
    
    public static void main(String[] args) {
        MemberDao dao = new MemberDao();
//        System.out.println(dao.changePass("testemail1@gmail.com", "test", "123456"));
//        System.out.println(dao.insertMember("name", "fullname", "123456", "testemail1@gmail.com", "test"));
//        System.out.println(dao.checkMemberEmail("doandinhlinhkma@gmail.com"));
//        System.out.println(dao.checkSession("bb3412c3b8cee848e12e7dcffec496fa"));
        System.out.println(dao.getIdMember("doandinhlinh.kma@gmail.com"));
    }

}
