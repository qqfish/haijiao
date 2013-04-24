/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.Mail;
import com.haijiao.SupportService.service.IMailService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hp
 */
public class GetMailAction extends SessionAction{
    IMailService mailService;
    List<Mail> allMailList;
    List<Mail> unreadMailList;

    public IMailService getMailService() {
        return mailService;
    }

    public void setMailService(IMailService mailService) {
        this.mailService = mailService;
    }

    public List<Mail> getAllMailList() {
        return allMailList;
    }

    public void setAllMailList(List<Mail> allMailList) {
        this.allMailList = allMailList;
    }

    public List<Mail> getUnreadMailList() {
        return unreadMailList;
    }

    public void setUnreadMailList(List<Mail> unreadMailList) {
        this.unreadMailList = unreadMailList;
    }
    
    @Override
    public String execute() throws Exception {
        allMailList = new ArrayList<Mail>();
        unreadMailList = new ArrayList<Mail>();
        String email = (String)this.getSessionValue("email");
        allMailList = mailService.getMail(email);
        unreadMailList = mailService.getUnreadMail(email);
        return SUCCESS;
    }
}
