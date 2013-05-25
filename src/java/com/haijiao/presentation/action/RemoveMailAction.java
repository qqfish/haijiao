/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.haijiao.SupportService.service.IMailService;
import static com.opensymphony.xwork2.Action.SUCCESS;

/**
 *
 * @author hp
 */
public class RemoveMailAction extends SessionAction{
    IMailService mailService;
    int id;
    
    public String remove(){
        //mailService.deleteMail(Integer.parseInt(id));
        mailService.deleteMail(id);
        return SUCCESS;
    }
    
    public String removeAll(){
        String email = (String) this.getSessionValue("email");
        mailService.deletaAll(email);
        return SUCCESS;
    }

    public void setMailService(IMailService mailService) {
        this.mailService = mailService;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
