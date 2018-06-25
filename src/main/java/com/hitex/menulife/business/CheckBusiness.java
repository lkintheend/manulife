/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hitex.menulife.business;

import com.hitex.menulife.dao.MemberDao;
import com.hitex.menulife.model.RequestData;

/**
 *
 * @author lkintheend
 */
public class CheckBusiness {
    public boolean checkSession(String session) {
        MemberDao memberDao = new  MemberDao();
        return memberDao.checkSession(session);
    }
}
