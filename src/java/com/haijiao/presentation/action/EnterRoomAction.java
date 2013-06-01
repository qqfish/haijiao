/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.User;
import com.haijiao.SupportService.service.IUserService;

/**
 *
 * @author fish
 */
public class EnterRoomAction extends RequestSessionAction{
    private IUserService userService;
    int clazzId;
    int isHolder;
    private User user;
    
    @Override
    public String execute(){
        clazzId = -1;
        clazzId = Integer.parseInt((String)this.getOutRequest("clazzId"));
        isHolder = 0;
        String userType = (String) this.getOutSession("userType");
        if( userType!=null && userType.equals("teacher")){
            isHolder = 1;
        }
        if(clazzId < 0){
            return "false";
        }
        String email = (String) this.getOutSession("email");
        if(email == null){
            return "false";
        }
        user = userService.getUserByEmail(email);
        return SUCCESS;
    }

    public int getClazzId() {
        return clazzId;
    }

    public void setClazzId(int clazzId) {
        this.clazzId = clazzId;
    }

    public int getIsHolder() {
        return isHolder;
    }

    public void setIsHolder(int isHolder) {
        this.isHolder = isHolder;
    }
    
}