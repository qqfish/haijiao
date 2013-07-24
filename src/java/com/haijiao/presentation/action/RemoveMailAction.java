/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.haijiao.SupportService.service.IMailService;
import static com.opensymphony.xwork2.Action.SUCCESS;
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
@Action("removeMail")
@Results({
    @Result(name="success",type="redirect",location="getMail.action")
})
public class RemoveMailAction extends SessionAction{
    @Resource
    private IMailService mailService;
    private Integer id;
    
    public String removeAll(){
        String email = (String) this.getSessionValue("email");
        mailService.deletaAll(email);
        return SUCCESS;
    }

    public void setMailService(IMailService mailService) {
        this.mailService = mailService;
    }

    public IMailService getMailService() {
        return mailService;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
}
