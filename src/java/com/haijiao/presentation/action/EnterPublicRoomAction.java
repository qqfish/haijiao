/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

/**
 *
 * @author fish
 */
public class EnterPublicRoomAction extends RequestSessionAction {
    String teaEmail;
    int isHolder;
    
    @Override
    public String execute(){
        teaEmail = (String)this.getOutRequest("teaEmail");
        isHolder = 0;
        if(teaEmail == null){
            return "false";
        }
        String email = (String) this.getOutSession("email");
        if(email == null){
            return "false";
        }
        if(email.equals(teaEmail)){
            isHolder = 1;
        }
        return SUCCESS;
    }

    public String getTeaEmail() {
        return teaEmail;
    }

    public void setTeaEmail(String teaEmail) {
        this.teaEmail = teaEmail;
    }

    public int getIsHolder() {
        return isHolder;
    }

    public void setIsHolder(int isHolder) {
        this.isHolder = isHolder;
    }
}
