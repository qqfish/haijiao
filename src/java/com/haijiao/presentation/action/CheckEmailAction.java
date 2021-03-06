/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.haijiao.SupportService.service.IUserService;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.stereotype.Controller;

/**
 *
 * @author hp
 */

@Controller
@ParentPackage("struts-default")
@Namespace("/")
@Action("checkEmail")
public class CheckEmailAction extends SessionAction{
    @Resource
    IUserService userService;
    String email;
    
    @Override
    public String execute(){
        HttpServletResponse response = ServletActionContext.getResponse();
        try {
            response.getWriter().write(String.valueOf(userService.confirmExist(email)));
        } catch (IOException ex) {
            Logger.getLogger(CheckEmailAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
}
