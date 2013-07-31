/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.Bill;
import com.haijiao.SupportService.service.IBillService;
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
@Action("createBill")
@Results({
    @Result(name = "success", location = "/confirmPay.jsp"),
    @Result(name = "input", type = "redirect", location = "index.action?")
})
public class CreateBillAction extends SessionAction {

    @Resource
    IBillService billService;
    int billId;
    double money;
    String message;

    public String accept() {
        Bill bill = billService.getBillById(billId);
        money = bill.getMoney();
        message = bill.getTeacher().getName() + "老师-" + bill.getLesson() + "课-" + bill.getDuration() + "小时";
        return SUCCESS;
    }
        
    public String deny(){
        billService.changeBillStatus(billId, Bill.Status.studentCancel);
        this.sessionPutIn("nextPageMessage","已拒绝预约。\n您的老师马上就会接到通知。\n");
        return INPUT;
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

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
