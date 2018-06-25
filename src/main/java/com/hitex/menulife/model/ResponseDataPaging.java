/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hitex.menulife.model;

/**
 *
 * @author lkintheend
 */
public class ResponseDataPaging extends ResponseData{
    
    int page;
    
    public ResponseDataPaging(String status, String msg, Object data, int page) {
        super(status, msg, data);
        this.page= page;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
    
    
    
}
