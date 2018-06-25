/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hitex.menulife.business;

import com.hitex.menulife.dao.MenuDao;
import com.hitex.menulife.model.MenuProducts;
import com.hitex.menulife.model.ProductsGroupExtends;
import java.util.ArrayList;

/**
 *
 * @author lkintheend
 */
public class MenuProductsBusiness {
    public ArrayList<ProductsGroupExtends> getMenu() {
        MenuDao menuDao = new MenuDao();
        return menuDao.getMenu();
    }
}
