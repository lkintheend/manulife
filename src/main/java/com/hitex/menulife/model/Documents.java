/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hitex.menulife.model;

import com.hitex.menulife.config.StringConfig;
import com.hitex.menulife.util.Util;

/**
 *
 * @author lkintheend
 */
public class Documents {
    String id;
    String name;
    String link;
    String status;
    String createdAt;
    String updatedAt;
    
    public Documents() {}

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

    public String getLink() {
        return StringConfig.domainUrl+link;
    }

    public void setLink(String link) {
        this.link = link;
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
        this.createdAt = Util.convertStringToTimestamp(createdAt);
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = Util.convertStringToTimestamp(updatedAt);
    }

    @Override
    public String toString() {
        return "Documents{" + "id=" + id + ", name=" + name + ", link=" + link + ", status=" + status + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }
    
    
}
