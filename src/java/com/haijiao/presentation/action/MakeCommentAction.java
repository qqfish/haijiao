/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.User;
import com.haijiao.SupportService.service.IUserService;
import static com.opensymphony.xwork2.Action.SUCCESS;

/**
 *
 * @author hp
 */
public class MakeCommentAction extends SessionAction{
    IUserService userService;
    private String email;
    private String content;
    
    @Override
    public String execute(){
        User u = (User) this.getValue("user");
        userService.comment(u.getEmail(), email, content, Integer.SIZE);
        return SUCCESS;
     }
    
    @Override
    public void validate(){
        if(content==null || content.trim().length()==0)
            this.addFieldError("content", this.getText("contetnt NULL"));
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
    
}
