/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hitex.menulife.model;

import com.hitex.menulife.config.StringConfig;

/**
 *
 * @author lkintheend
 */
public class Product {
    String id;
    String name;
    String slugName;
    String description;
    String img;
    String updatedAt;

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updateAt) {
        this.updatedAt = updateAt;
    }

    public Product() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlugName() {
        return slugName;
    }

    public void setSlugName(String slugName) {
        this.slugName = slugName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = StringConfig.domainUrl+img;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", slugName=" + slugName + ", description=" + description + ", img=" + img + '}';
    }
    
    
}
