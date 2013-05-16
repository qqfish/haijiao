/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.haijiao.SupportService.service.IMailService;

/**
 *
 * @author hp
 */
public class DeleteMailAction extends SessionAction{
    IMailService mailService;
    String id;
    
    @Override
    public String execute(){
        mailService.deleteMail(Integer.parseInt(id));
        return SUCCESS;
    }

    public void setMailService(IMailService mailService) {
        this.mailService = mailService;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
}
