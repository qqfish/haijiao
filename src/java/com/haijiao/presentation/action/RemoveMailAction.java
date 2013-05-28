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
    private IMailService mailService;
    private Integer id;
    
    public String removeAll(){
        String email = (String) this.getSessionValue("email");
        mailService.deletaAll(email);
        return SUCCESS;
    }

    public void setMailService(IMailService mailService) {
        this.mailService = mailService;
    }

    public IMailService getMailService() {
        return mailService;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
}
