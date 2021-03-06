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
import org.springframework.transaction.annotation.Propagation;
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

    public void setMailDAO(IMailDAO mailDAO) {
        this.mailDAO = mailDAO;
    }

    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }
    
    @Override
    @Transactional(propagation=Propagation.SUPPORTS)
    public Mail getMailById(int id) {
        return mailDAO.findById(id);
    }

    @Override
    @Transactional(propagation=Propagation.SUPPORTS)
    public List<Mail> getMail(String email) {
        return mailDAO.getMailByEmail(email);
    }

    @Override
    @Transactional(propagation=Propagation.SUPPORTS)
    public List<Mail> getUnreadMail(String email){
        return mailDAO.getUnreadMailByEmail(email);
    }
    
    @Override
    @Transactional(propagation=Propagation.SUPPORTS)
    public int getUnreadMailNum(String email){
        return mailDAO.getUnreadMailNum(email);
    }
    
    @Override
    public boolean sendMail(String emailFrom, int id, String message) {
        Mail m = new Mail();
        User from = userDAO.getUserByEmail(emailFrom);
        User to = userDAO.findById(id);
        m.setFrom(from);
        m.setMessage(message);
        m.setRead(false);
        m.setTo(to);
        return mailDAO.makePersistent(m);
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
    
    @Override
    public boolean setAllMailStatus(String email){
        mailDAO.setAllMailStatus(email);
        return true;
    }
    
    @Override
    public boolean deleteMail(int mailId) {
        Mail m = mailDAO.findById(mailId);
        return mailDAO.makeTransient(m);
    }

    @Override
    public boolean deletaAll(String email) {
        mailDAO.removeAllMail(email);
        return true;
    }
    
}
