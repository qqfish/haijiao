/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

/**
 *
 * @author fish
 */
public class EnterRoomAction extends RequestSessionAction{
    int clazzId;
    String email;
    
    @Override
    public String execute(){
        clazzId = -1;
        clazzId = Integer.parseInt((String)this.getOutRequest("clazzId"));
        if(clazzId < 0){
            return "false";
        }
        email = (String)this.getOutSession("email");
        if(email == null){
            return "false";
        }
        return SUCCESS;
    }

    public int getClazzId() {
        return clazzId;
    }

    public void setClazzId(int clazzId) {
        this.clazzId = clazzId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}