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
public class MarkMailAction extends SessionAction {

    IMailService mailService;
    String id;
    
    @Override
    public String execute() {
        mailService.setMailStatus(Integer.parseInt(id), true);
        return null;
    }
    
    public String markAll(){
        String email = (String) this.getSessionValue("email");
        mailService.setAllMailStatus(email);
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
