/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.User;
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
@Action("enterRoom")
@Results({
    @Result(name="success",location="/room/index.jsp"),
    @Result(name="false",type="chain",location="index")
})
public class EnterRoomAction extends RequestSessionAction{
    @Resource
    private IUserService userService;
    private String teaEmail;
    private String stuEmail;
    private String email;
    private int isHolder;
    private User user;
    
    @Override
    public String execute(){
        isHolder = 0;
        teaEmail = (String) this.getOutRequest("teaEmail");
        stuEmail = (String) this.getOutRequest("stuEmail");
        if(teaEmail == null || stuEmail == null)
            return "false";
        String userType = (String) this.getOutSession("userType");
        if( userType!=null && userType.equals("teacher")){
            isHolder = 1;
        }
        String email = (String) this.getOutSession("email");
        if(email == null){
            return "false";
        }
        user = userService.getUserByEmail(email);
        return SUCCESS;
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public String getTeaEmail() {
        return teaEmail;
    }

    public void setTeaEmail(String teaEmail) {
        this.teaEmail = teaEmail;
    }

    public String getStuEmail() {
        return stuEmail;
    }

    public void setStuEmail(String stuEmail) {
        this.stuEmail = stuEmail;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    public int getIsHolder() {
        return isHolder;
    }

    public void setIsHolder(int isHolder) {
        this.isHolder = isHolder;
    }
    
}