/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.service.impl;

import com.haijiao.Domain.bean.Mail;
import com.haijiao.Domain.bean.User;
import com.haijiao.SupportService.dao.IMailDAO;
import com.haijiao.SupportService.dao.IUserDAO;
import com.haijiao.SupportService.service.IMailService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author hp
 */

@Service
@Transactional
public class MailServiceImpl implements IMailService{
    @Resource
    IMailDAO mailDAO;
    @Resource
    IUserDAO userDAO;
    
    @Override
    public Mail getMailById(int id) {
        return mailDAO.findById(id);
    }

    @Override
    public List<Mail> getMail(String email) {
        return mailDAO.getMailByEmail(email);
    }

    @Override
    public boolean sendMail(String emailFrom, String emailTo, String message) {
        Mail m = new Mail();
        User from = userDAO.getUserByEmail(emailFrom);
        User to = userDAO.getUserByEmail(emailTo);
        m.setFrom(from);
        m.setMessage(message);
        m.setRead(false);
        m.setTo(to);
        return mailDAO.makePersistent(m);
    }

    @Override
    public boolean setMailStatus(int mailId, boolean read) {
        Mail m = mailDAO.findById(mailId);
        m.setRead(read);
        mailDAO.update(m);
        return true;
    }
    
}
