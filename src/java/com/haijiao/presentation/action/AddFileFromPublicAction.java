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
@Action("addFileFromPublic")
@Results({
    @Result(name = "success", type = "redirect", location = "index.action")
})
public class AddFileFromPublicAction extends SessionAction{
    @Resource
    IUserService userService;
    private String groupName;
    int publicFileId;
    
    @Override
    public String execute(){ 
        String email = (String) this.getSessionValue("email");
        userService.addFileFromPublic(email, groupName, publicFileId);
        return SUCCESS;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getPublicFileId() {
        return publicFileId;
    }

    public void setPublicFileId(int publicFileId) {
        this.publicFileId = publicFileId;
    }
    
}
