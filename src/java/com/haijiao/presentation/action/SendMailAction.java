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
@Action("sendMail")
@Results({
    @Result(name="input",type="redirect",location="getMail.action"),
    @Result(name="success",type="redirect",location="getMail.action")
})
public class SendMailAction extends SessionAction{
    private String name;
    @Resource
    private IMailService mailService;
    private String email;
    private String content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IMailService getMailService() {
        return mailService;
    }

    public void setMailService(IMailService mailService) {
        this.mailService = mailService;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    @Override
    public String execute(){
        if(name.isEmpty()){
            this.sessionPutIn("nextPageMessage", "发送失败，收件人不能为空");
            return INPUT;
        }
        if(content.isEmpty()){
            this.sessionPutIn("nextPageMessage", "发送失败，消息内容不能为空");
            return INPUT;
        }
        mailService.sendMail((String)this.getSessionValue("email"), name, content);
        this.sessionPutIn("nextPageMessage", "发送成功");
        return SUCCESS;
    }
}
