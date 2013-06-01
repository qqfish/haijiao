/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.User;
import com.haijiao.SupportService.service.ITeacherService;
import com.haijiao.SupportService.service.IUserService;

/**
 *
 * @author fish
 */
public class EnterPublicRoomAction extends RequestSessionAction {
    private ITeacherService teacherService;
    private IUserService userService;
    private String teaEmail;
    private int isHolder;
    private User user;
    
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
        user = userService.getUserByEmail(email);
        if(email.equals(teaEmail)){
            isHolder = 1;
        }
        if(!teaEmail.equals((String)this.getOutSession("email"))){
            teacherService.setRoomOccupied(teaEmail, true);
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

    public ITeacherService getTeacherService() {
        return teacherService;
    }

    public void setTeacherService(ITeacherService teacherService) {
        this.teacherService = teacherService;
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
