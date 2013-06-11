/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.User;
import com.haijiao.SupportService.service.ITeacherService;
import com.haijiao.SupportService.service.IUserService;
import javax.annotation.Resource;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

/**
 *
 * @author fish
 */
@Controller
@ParentPackage("haijiao")
@Namespace("/")
@InterceptorRefs({  
    @InterceptorRef("LoginCheckerStack")  
})  
@Action("enterPublicRoom")
@Results({
    @Result(name="success",location="/room/index.jsp"),
    @Result(name="false",type="chain",location="index")
})
public class EnterPublicRoomAction extends RequestSessionAction {
    @Resource
    private ITeacherService teacherService;
    @Resource
    private IUserService userService;
    private String teaEmail;
    private int isHolder;
    private User user;
    private String nextPageMessage;

    @Override
    public String execute() {
        teaEmail = (String) this.getOutRequest("teaEmail");
        isHolder = 0;
        if (teaEmail == null) {
            return "false";
        }
        String email = (String) this.getOutSession("email");
        if (email == null) {
            return "false";
        }
        user = userService.getUserByEmail(email);
        if (email.equals(teaEmail)) {
            isHolder = 1;
        }
        if (!teaEmail.equals((String) this.getOutSession("email"))) {
            if (teacherService.getRoomOccupied(teaEmail) != null && !teacherService.getRoomOccupied(teaEmail).equals(email)) {
                nextPageMessage = "房间已被占用，请稍后再试。";
                return "false";
            }
            teacherService.setRoomOccupied(teaEmail, email);
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
