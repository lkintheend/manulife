/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hitex.menulife.business;

import com.hitex.menulife.dao.ProductDao;
import com.hitex.menulife.model.Product;
import java.util.ArrayList;

/**
 *
 * @author lkintheend
 */
public class ProductBusiness {

    public ArrayList<Product> getAllProduct(int offset, int limit) {
        ArrayList<Product> lstProduct = new ArrayList<>();
        ProductDao dao = new ProductDao();
        lstProduct = dao.getAllProducts(offset, limit);
        return lstProduct;
    }
    
    public ArrayList<Product> getTopProduct() {
        ArrayList<Product> lstProduct;
        ProductDao dao = new ProductDao();
        lstProduct = dao.getTopProducts();
        return lstProduct;
    }
    
    public Product getProductById(int id) {
        ProductDao dao = new ProductDao();
        return dao.getProductById(id);
    }
    
    public Product getIntro() {
        ProductDao dao = new ProductDao();
        return dao.getIntro();
    }
    
    public int countRow(){
        ProductDao dao = new ProductDao();
        return dao.countRow();
    }
}
