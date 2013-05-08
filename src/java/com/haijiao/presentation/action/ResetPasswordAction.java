/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.haijiao.SupportService.service.IUserService;

/**
 *
 * @author hp
 */
public class ResetPasswordAction extends RequestAction{
    String id;
    String checkcode;
    IUserService userService;
    
    @Override
    public String execute(){
        id = (String) this.getRequestValue("id");
        checkcode = (String) this.getRequestValue("checkCode");
        checkcode = checkcode.replace(" ", "+");
        if(userService.validateCheckcode(Integer.parseInt(id), checkcode))
            return SUCCESS;
        else
            return INPUT;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCheckcode() {
        return checkcode;
    }

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }
    
}
