    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hitex.menulife.util;

import com.hitex.menulife.config.EmailConfig;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

/**
 *
 * @author lkintheend
 */
public class EmailCreate {

    public void newEmail(String to, String rememberToken) {
        Email email = new SimpleEmail();
        try {
//            email.setHostName(EmailConfig.HOST_NAME);
            email.setHostName("smtp.live.com");
            email.setSmtpPort(587);
            email.setAuthenticator(new DefaultAuthenticator(EmailConfig.MY_EMAIL, EmailConfig.MY_PASSWORD));
            //neu cai smtpPort 465 (port 1)
//        email.setSSLOnConnect(true);
            email.setTLS(true); //neu cai SmtpPort 587 (port 2)
            email.setFrom(EmailConfig.MY_EMAIL);
            email.setSubject("Email xác nhận đăng kí tài khoản manulife!!");
            email.setMsg("Click Link: http://localhost:8080/api?token=" + rememberToken);
            email.addTo(to);
            System.out.println(email.toString());
            email.send();
        } catch (EmailException ex) {
            Logger.getLogger(EmailCreate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void forgotPassMail(String to, String id) throws Exception {
        Email email = new SimpleEmail();
        try {
//            email.setHostName(EmailConfig.HOST_NAME);
            email.setHostName("smtp.live.com");
            email.setSmtpPort(587);
            email.setAuthenticator(new DefaultAuthenticator(EmailConfig.MY_EMAIL, EmailConfig.MY_PASSWORD));
            //neu cai smtpPort 465 (port 1)
//        email.setSSLOnConnect(true);
            email.setTLS(true); //neu cai SmtpPort 587 (port 2)
            email.setFrom(EmailConfig.MY_EMAIL);
            email.setSubject("Click link de doi mk!!");
            email.setMsg("Click Link: http://localhost:8080/api?token=" + StringEncrypt.getInstance().encrypt(id));
            email.addTo(to);
            System.out.println(email.toString());
            email.send();
        } catch (EmailException ex) {
            Logger.getLogger(EmailCreate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws EmailException {
        EmailCreate emailCreate = new EmailCreate();
        emailCreate.newEmail("doandinhlinh.kma@gmail.com", "test2");
    }
}
