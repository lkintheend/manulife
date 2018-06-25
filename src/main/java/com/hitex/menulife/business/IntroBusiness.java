/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hitex.menulife.business;

import com.hitex.menulife.dao.IntroDao;
import com.hitex.menulife.model.Intro;

/**
 *
 * @author lkintheend
 */
public class IntroBusiness {
    public Intro getIntro() {
        IntroDao introDao = new IntroDao();
        return introDao.getIntro();
    }
}
