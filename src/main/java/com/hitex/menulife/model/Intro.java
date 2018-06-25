/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hitex.menulife.model;

import com.hitex.menulife.util.Util;

/**
 *
 * @author lkintheend
 */
public class Intro {

    String id;
    String content;
    String createdAt;
    String updatedAt;

    public Intro() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        return "Intro{" + "id=" + id + ", content=" + content + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }
    
}
