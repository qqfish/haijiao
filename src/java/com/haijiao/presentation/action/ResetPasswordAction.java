/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.haijiao.SupportService.service.IUserService;
import javax.annotation.Resource;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

/**
 *
 * @author hp
 */
@Controller
@ParentPackage("struts-default")
@Namespace("/")
@Action("resetPassword")
@Results({
    @Result(name="success",location="/resetPassword.jsp")
})
public class ResetPasswordAction extends RequestAction{
    @Resource
    private IUserService userService;
    private String id;
    private String checkcode;
    private String nextPageMessage;
    
    @Override
    public String execute(){
        id = (String) this.getRequestValue("id");
        checkcode = (String) this.getRequestValue("checkCode");
        checkcode = checkcode.replace(" ", "+");
        if(userService.validateCheckcode(Integer.parseInt(id), checkcode)){
            return SUCCESS;
        }
        else{
            nextPageMessage = "无效的地址";
            return INPUT;
        }
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCheckcode() {
        return checkcode;
    }

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }

    public String getNextPageMessage() {
        return nextPageMessage;
    }

    public void setNextPageMessage(String nextPageMessage) {
        this.nextPageMessage = nextPageMessage;
    }
    
}
