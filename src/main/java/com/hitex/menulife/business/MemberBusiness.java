/*t
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hitex.menulife.business;

import com.hitex.menulife.config.StringConfig;
import com.hitex.menulife.dao.MemberDao;
import com.hitex.menulife.model.Member;
import com.hitex.menulife.model.ResponseData;
import com.hitex.menulife.util.Util;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class MemberBusiness {

    public ArrayList<String> getNameMembers() {
        ArrayList<String> lstName = new ArrayList<>();
        MemberDao membersDao = new MemberDao();
        lstName = membersDao.getNameMembers();
        return lstName;
    }

    public String insertMember(String name, String fullName, String passWord, String phone, String email) {
        MemberDao membersDao = new MemberDao();
        return membersDao.insertMember(name, fullName, passWord, phone, email);
    }

    public Member validateMember(String email, String passWord) {
        MemberDao membersDao = new MemberDao();
        return membersDao.validateMember(email, passWord);
    }
    
    public Member getMemberInfo(String email) {
        MemberDao membersDao = new MemberDao();
        return membersDao.getMemberInfo(email);
    }

    public ResponseData checkMember(Member member) {

        ResponseData responseData = new ResponseData("1", "khong xac dinh", null);
        
        if (member.getStatus() == null) {
            responseData = new ResponseData("1", "Username or password invalid", null);
        } else if (Integer.valueOf(member.getStatus()) == 0) {
            responseData = new ResponseData("1", "This user is not actived", null);
        } else if (Integer.valueOf(member.getStatus()) == 1) {
            responseData = new ResponseData("0", "thanh cong", member);
        }
        
        return responseData;
    }
    
    public boolean activeMember(String rememberToken){
        MemberDao memberDao = new  MemberDao();       
        return memberDao.activeUser(rememberToken);
    }
    
    public boolean changePass(String email, String oldPassWord, String newPassWord) {
        MemberDao memberDao = new  MemberDao();      
        return memberDao.changePass(email, oldPassWord, newPassWord);
    }
    
    public boolean checkMemberEmail(String email){
        MemberDao memberDao = new  MemberDao();  
        return memberDao.checkMemberEmail(email);
    }
    
    public boolean updateProfile(String email, String fullName, String sex, String birthday, String phone) {
        MemberDao memberDao = new  MemberDao(); 
        return memberDao.updateProfile(email, fullName, sex, birthday, phone);
    }
    
    public String forgotPass(String idEncrypt) throws Exception {
        String randomPass = Util.RandomPassword();
        MemberDao memberDao = new  MemberDao(); 
        memberDao.forgotPass(idEncrypt, randomPass);
        return randomPass;
    }
    
    /*Lay id tu email de forgot password*/
    public String getIdMember(String email){
        MemberDao memberDao = new  MemberDao(); 
        return memberDao.getIdMember(email);
    }
    
}
