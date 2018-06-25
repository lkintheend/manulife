/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hitex.menulife.util;

import org.apache.commons.mail.EmailException;

/**
 *
 * @author lkintheend
 */
public class Test {
    public static void main(String[] args) throws EmailException {
        EmailCreate emailCreate = new EmailCreate();
            emailCreate.newEmail("doandinhlinh.kma@gmail.com", "12324");
    }
}
