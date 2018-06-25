/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hitex.menulife.model;

import java.util.ArrayList;

/**
 *
 * @author lkintheend
 */
public class MenuProducts {

    ProductsGroup productsGroup;
    ArrayList<Product> listProducts;

    public MenuProducts() {
    }

    public ProductsGroup getProductsGroup() {
        return productsGroup;
    }

    public void setProductsGroup(ProductsGroup productsGroup) {
        this.productsGroup = productsGroup;
    }

    public ArrayList<Product> getListProducts() {
        return listProducts;
    }

    public void setListProducts(ArrayList<Product> listProducts) {
        this.listProducts = listProducts;
    }

    @Override
    public String toString() {
        return "MenuProducts{" + "productsGroup=" + productsGroup + ", listProducts=" + listProducts + '}';
    }
    
}
