/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hitex.menulife.business;

import com.hitex.menulife.dao.ProductsGroupDao;
import com.hitex.menulife.model.ProductsGroup;
import java.util.ArrayList;

/**
 *
 * @author lkintheend
 */
public class ProductsGroupBusiness {
    public ArrayList<ProductsGroup> getAllProductsGroup(int offset, int limit) {
        ArrayList<ProductsGroup> listProductGroup = new ArrayList<>();
        ProductsGroupDao dao = new ProductsGroupDao();
        listProductGroup = dao.getAllProductsGroup(offset, limit);
        return listProductGroup;
    }
    
    public ProductsGroup getProductsGroupById(int id) {
        ProductsGroupDao dao = new ProductsGroupDao();
        return dao.getProductsGroupById(id);
    }
    
    public int countRow(){
        ProductsGroupDao dao = new ProductsGroupDao();
        return dao.countRow();
    }
    
}
