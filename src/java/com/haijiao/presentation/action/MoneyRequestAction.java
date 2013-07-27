/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.haijiao.SupportService.service.ITeacherService;
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
@Action("moneyRequest")
@Results({
    @Result(name="success",type="redirect",location="index.action")
})
public class MoneyRequestAction extends RequestSessionAction{
    private String bankcard;
    private String bankname;
    @Resource
    private ITeacherService teacherService;
    
    public String temp(){
        String email = (String) this.getOutSession("email");
        teacherService.requestMoney(email, bankcard, bankname);
        this.sessionPutIn("nextPageMessage", "请求成功，请耐心等待");
        return SUCCESS;
    }
    public String save(){
        String email = (String) this.getOutSession("email");
        teacherService.requestMoneyAndUpdate(email, bankcard, bankname);
        this.sessionPutIn("nextPageMessage", "请求成功，请耐心等待");
        return SUCCESS;
    }

    public String getBankcard() {
        return bankcard;
    }

    public void setBankcard(String bankcard) {
        this.bankcard = bankcard;
    }

    public ITeacherService getTeacherService() {
        return teacherService;
    }

    public void setTeacherService(ITeacherService teacherService) {
        this.teacherService = teacherService;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }
}
