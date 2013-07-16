/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.Bill;
import com.haijiao.SupportService.service.IBillService;
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
@Action("dealWithReservation")
@Results({
    @Result(name="input",location="/teacherIndex.jsp"),
    @Result(name="success",type="chain",location="index")
})
public class DealWithReservationAction extends SessionAction{
    @Resource
    private IBillService billService;
    String nextPageMessage;
    int billId;

    public String accept(){
        billService.changeBillStatus(billId, Bill.Status.accept);
        nextPageMessage = "已确认预约。\n您的学生马上就会接到通知。\n";
        return SUCCESS;
    }
    
    public String deny(){
        billService.changeBillStatus(billId, Bill.Status.notAccept);
        nextPageMessage = "已拒绝预约。\n您的学生马上就会接到通知。\n";
        return SUCCESS;
    }
    
    public void setBillService(IBillService billService) {
        this.billService = billService;
    }

    public String getNextPageMessage() {
        return nextPageMessage;
    }

    public void setNextPageMessage(String nextPageMessage) {
        this.nextPageMessage = nextPageMessage;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }
    
}
