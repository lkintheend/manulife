/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hitex.menulife.business;

import com.hitex.menulife.dao.DocumentsDao;
import com.hitex.menulife.model.Documents;
import java.util.ArrayList;

/**
 *
 * @author lkintheend
 */
public class DocumentsBusiness {
    public ArrayList<Documents> getAllDocuments(int offset, int limit) {
        DocumentsDao dao = new DocumentsDao();
        return dao.getAllDocuments(offset, limit);
    }
    
    public Documents getDocumentsById(int id) {
        DocumentsDao dao = new DocumentsDao();
        return dao.getDocumentsById(id);
    }
    
    public int countRow(){
        DocumentsDao dao = new DocumentsDao();
        return dao.countRow();
    }
}
