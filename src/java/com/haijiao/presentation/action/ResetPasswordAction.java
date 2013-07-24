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
public class ResetPasswordAction extends RequestSessionAction{
    @Resource
    private IUserService userService;
    private String id;
    private String checkcode;
    
    @Override
    public String execute(){
        id = (String) this.getOutRequest("id");
        checkcode = (String) this.getOutRequest("checkCode");
        checkcode = checkcode.replace(" ", "+");
        if(userService.validateCheckcode(Integer.parseInt(id), checkcode)){
            return SUCCESS;
        }
        else{
            this.sessionPutIn("nextPageMessage", "无效的地址");
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
}
