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
public class ProductsPromotions {

    String id;
    String name;
    String slugName;
    String description;
    String content;
    String quantityGift;
    String dateTimeFrom;
    String dateTimeTo;
    String quantityReceiver;
    String image;
    String status;
    String userCreatedId;
    String createdAt;
    String updatedAt;
    String price;

    public ProductsPromotions() {
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getQuantityGift() {
        return quantityGift;
    }

    public void setQuantityGift(String quantityGift) {
        this.quantityGift = quantityGift;
    }

    public String getDateTimeFrom() {
        return dateTimeFrom;
    }

    public void setDateTimeFrom(String dateTimeFrom) {
        this.dateTimeFrom = dateTimeFrom;
    }

    public String getDateTimeTo() {
        return dateTimeTo;
    }

    public void setDateTimeTo(String dateTimeTo) {
        this.dateTimeTo = dateTimeTo;
    }

    public String getQuantityReceiver() {
        return quantityReceiver;
    }

    public void setQuantityReceiver(String quantityReceiver) {
        this.quantityReceiver = quantityReceiver;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = StringConfig.domainUrl+image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserCreatedId() {
        return userCreatedId;
    }

    public void setUserCreatedId(String userCreatedId) {
        this.userCreatedId = userCreatedId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "ProductsPromotions{" + "id=" + id + ", name=" + name + ", slugName=" + slugName + ", description=" + description + ", content=" + content + ", quantityGift=" + quantityGift + ", dateTimeFrom=" + dateTimeFrom + ", dateTimeTo=" + dateTimeTo + ", quantityReceiver=" + quantityReceiver + ", image=" + image + ", status=" + status + ", userCreatedId=" + userCreatedId + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }

}
