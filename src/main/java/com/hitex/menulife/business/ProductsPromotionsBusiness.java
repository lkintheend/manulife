/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hitex.menulife.business;

import com.hitex.menulife.dao.ProductsPromotionsDao;
import com.hitex.menulife.model.ProductsPromotions;
import java.util.ArrayList;

/**
 *
 * @author lkintheend
 */
public class ProductsPromotionsBusiness {

    public ArrayList<ProductsPromotions> getAllProductssPromotions(int offset, int limit) {
        ProductsPromotionsDao dao = new ProductsPromotionsDao();
        return dao.getAllProductssPromotions(offset, limit);
    }
    
    public ArrayList<ProductsPromotions> getTopProductssPromotions() {
        ProductsPromotionsDao dao = new ProductsPromotionsDao();
        return dao.getTopProductssPromotions();
    }
    
    public ProductsPromotions getProductsPromotionsById(int id) {
        ProductsPromotionsDao dao = new ProductsPromotionsDao();
        return dao.getProductsPromotionsById(id);
    }
    
     public int countRow(){
        ProductsPromotionsDao dao = new ProductsPromotionsDao();
        return dao.countRow();
    }
    
}
