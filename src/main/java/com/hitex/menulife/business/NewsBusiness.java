/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hitex.menulife.business;

import com.hitex.menulife.dao.NewsDao;
import com.hitex.menulife.model.News;
import java.util.ArrayList;

/**
 *
 * @author lkintheend
 */
public class NewsBusiness {
    public ArrayList<News> getAllNews(int offset, int limit) {
        ArrayList<News> lstNews = new ArrayList<>();
        NewsDao dao = new NewsDao();
        lstNews = dao.getAllNews(offset, limit);
        return lstNews;
    }
    
    public News getNewsById(int id) {
        NewsDao dao = new NewsDao();
        return dao.getNewsById(id);
    }
    
    public int countRow(){
        NewsDao dao = new NewsDao();
        return dao.countRow();
    }
    
    public News getIntro() {
        NewsDao dao = new NewsDao();
        return dao.getIntro();
    }
    
    public News getCustomerCare() {
        NewsDao dao = new NewsDao();
        return dao.getCustomerCare();
    }
}
