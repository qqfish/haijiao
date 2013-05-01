/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.haijiao.SupportService.service.IBillService;
import com.haijiao.SupportService.service.IUserService;

/**
 *
 * @author hp
 */
public class MakeCommentAction extends SessionAction{
    IBillService billService;
    IUserService userService;
    String content;
    String score;
    String id;
    
    @Override
    public String execute(){
        System.out.println(id);
        System.out.println(content);
        System.out.println(score);
        billService.commentBill(Integer.parseInt(id), content, Integer.parseInt(score));
        return SUCCESS;
    }

    public void setBillService(IBillService billService) {
        this.billService = billService;
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
}
