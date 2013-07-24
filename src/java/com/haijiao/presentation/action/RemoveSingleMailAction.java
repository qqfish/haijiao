/**
 *
 * @author Jerry Zou
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

@Controller
@ParentPackage("haijiao")
@Namespace("/")
@InterceptorRefs({
    @InterceptorRef("LoginCheckerStack")
})
@Action("removeSingleMail")
@Results({
    @Result(name="success",type="redirect",location="getMail.action")
})
public class RemoveSingleMailAction extends RequestAction{
    @Resource
    private IMailService mailService;
    private Integer id;
    
    @Override
    public String execute(){
        id = Integer.parseInt((String)this.getRequestValue("id"));
        mailService.deleteMail(id);
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
