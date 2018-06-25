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
public class ProductsGroup {
    String id;
    String name;
    String slugName;
    String startus;
    String createdAt;
    String updatedAt;

    public ProductsGroup() {}

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

    public String getStartus() {
        return startus;
    }

    public void setStartus(String startus) {
        this.startus = startus;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAT) {
        this.createdAt = createdAT;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "ProductsGroup{" + "id=" + id + ", name=" + name + ", slugName=" + slugName + ", startus=" + startus + ", createdAT=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }
            
}
