/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.Bill;
import com.haijiao.SupportService.service.IBillService;
import com.haijiao.presentation.action.SessionAction;
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
@Action("teacherConfirmFinish")
@Results({
    @Result(name="success",type="redirect",location="index.action?tab=bill")
})
public class TeacherConfirmFinishAction extends SessionAction{
    @Resource
    private IBillService billService;
    int billId;
    
    @Override
    public String execute(){
        billService.changeBillStatus(billId, Bill.Status.teacherFinish);
        return SUCCESS;
    }

    public void setBillService(IBillService billService) {
        this.billService = billService;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }
    
}
