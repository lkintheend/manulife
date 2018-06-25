/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hitex.menulife.business;

import com.hitex.menulife.dao.NotificationDao;
import com.hitex.menulife.model.Notification;
import java.util.ArrayList;

/**
 *
 * @author lkintheend
 */
public class NotificationBusiness {

    public ArrayList<Notification> getListNotificationByUserId(String userId, int offset, int limit) {
        NotificationDao nd = new NotificationDao();
        return nd.getListNotificationByUserId(userId, offset, limit);
    }

    public int countNotificationUnseenByUserId(String userId) {
        NotificationDao nd = new NotificationDao();
        return nd.countNotificationUnseenByUserId(userId);
    }

    public Notification getDetailNotificationById(String id) {
        NotificationDao nd = new NotificationDao();
        return nd.getDetailNotificationById(id);
    }

    public void updateSeenNotification(String id) {
        NotificationDao nd = new NotificationDao();
        nd.updateSeenNotification(id);
    }

    public int countRow() {
        NotificationDao nd = new NotificationDao();
        return nd.countRow();
    }
    
    public int countRowByUserId(String userId) {
        NotificationDao nd = new NotificationDao();
        return nd.countRowById(userId);
    }

}
