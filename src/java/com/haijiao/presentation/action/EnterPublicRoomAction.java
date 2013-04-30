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
    
    @Override
    public String execute(){
        teaEmail = (String)this.getOutRequest("teaEmail");
        if(teaEmail == null){
            return "false";
        }
        return SUCCESS;
    }

    public String getTeaEmail() {
        return teaEmail;
    }

    public void setTeaEmail(String teaEmail) {
        this.teaEmail = teaEmail;
    }
}
