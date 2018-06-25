/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hitex.menulife.business;

import com.hitex.menulife.dao.SlideDao;
import com.hitex.menulife.model.Slide;
import java.util.ArrayList;

/**
 *
 * @author lkintheend
 */
public class SlideBusiness {
    public ArrayList<Slide> getListSlides() {
        SlideDao slideDao = new SlideDao();
        return slideDao.getListSlides();
    }
}
