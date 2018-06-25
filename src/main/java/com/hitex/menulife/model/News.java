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
public class News {
    String id;
    String title;
    String slugName;
    String description;
    String content;
    String image;
    String userCreatedId;
    String status;
    String createdAt;
    String updatedAt;
    String idGroupNews;

    public String getIdGroupNews() {
        return idGroupNews;
    }

    public void setIdGroupNews(String idGroupNews) {
        this.idGroupNews = idGroupNews;
    }
    
    public News() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = StringConfig.domainUrl+image;
    }

    public String getUserCreatedId() {
        return userCreatedId;
    }

    public void setUserCreatedId(String userCreatedId) {
        this.userCreatedId = userCreatedId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
        return "News{" + "id=" + id + ", title=" + title + ", slugName=" + slugName + ", description=" + description + ", content=" + content + ", image=" + image + ", userCreatedId=" + userCreatedId + ", status=" + status + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }
        
}
