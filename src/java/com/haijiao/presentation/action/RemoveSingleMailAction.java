/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.haijiao.SupportService.service.IMailService;

public class RemoveSingleMailAction extends RequestAction{
private IMailService mailService;
    private Integer id;
    
    @Override
    public String execute(){
        id = Integer.parseInt((String)this.getRequestValue("id"));
        mailService.deleteMail(id);
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
