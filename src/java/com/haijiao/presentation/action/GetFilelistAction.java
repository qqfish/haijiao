/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.haijiao.Domain.file.UserFile;
import com.haijiao.Domain.file.UserFileGroup;
import com.haijiao.SupportService.service.IUserService;
import com.haijiao.global.PageBean;
import java.util.List;
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
@Action("getFilelist")
@Results({
    @Result(name="success",location="/userfilepart.jsp")
})
public class GetFilelistAction extends SessionAction{
    @Resource
    IUserService userService;
    private boolean isDir;
    private String groupName;
    private PageBean pb;
    
    @Override
    public String execute(){
        String email = (String) this.getSessionValue("email");
        if(isDir){
            List<UserFileGroup> ufg = userService.getUserFileGroup(email);
            pb = new PageBean(ufg, ufg.size(), 1, ufg.size());
        }
        else{
            List<UserFile> uf = userService.getUserFile(email, groupName);
            if(!uf.isEmpty())
                pb = new PageBean(uf, uf.size(), 1, uf.size());
        }
        return SUCCESS;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public boolean isIsDir() {
        return isDir;
    }

    public void setIsDir(boolean isDir) {
        this.isDir = isDir;
    }

    public PageBean getPb() {
        return pb;
    }

    public void setPb(PageBean pb) {
        this.pb = pb;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    
}
