/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.haijiao.SupportService.service.IMailService;
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
 * @author hp
 */
@Controller
@ParentPackage("haijiao")
@Namespace("/")
@InterceptorRefs({
    @InterceptorRef("LoginCheckerStack")
})
@Action("markMail")
public class MarkMailAction extends SessionAction {
    @Resource
    private IMailService mailService;
    private String id;

    @Override
    public String execute() {
        mailService.setMailStatus(Integer.parseInt(id), true);
        return null;
    }

    @Action(results = {
        @Result(name = "success", type="chain", location = "/getMail")
    })
    public String markAll() {
        String email = (String) this.getSessionValue("email");
        mailService.setAllMailStatus(email);
        return SUCCESS;
    }

    public void setMailService(IMailService mailService) {
        this.mailService = mailService;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
