/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.bean.mail;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author hp
 */
public class SendMail {
    protected Session session = null;
    private String from = "haijiaoedu@163.com";
    private String host = "smtp.163.com";
    private String username = "haijiaoedu";
    private String password = "haijiao";

    public SendMail() {
        Properties props = new Properties();
        props.put("mail.transpost.protocol", "smtp");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        MailAuthenticator auth =  new MailAuthenticator(username,password);
        session = Session.getInstance(props,auth);
        session.setDebug(true);
    }
    
    public void send(String email, String subject, String text){
        try {
            Message msg = new MimeMessage(session);
            msg.setHeader("Content-Type", "text/plain; charset=UTF-8");
            msg.setFrom(new InternetAddress(from));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            msg.setSubject(subject);
            msg.setText(text);
            Transport.send(msg);
        } catch (MessagingException ex) {
            Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
