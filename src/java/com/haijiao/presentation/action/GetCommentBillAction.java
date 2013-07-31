/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.Bill;
import com.haijiao.SupportService.service.IBillService;
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
@Action("getCommentBill")
@Results({
    @Result(name = "teacher", location = "/teachercommentpart.jsp"),
    @Result(name = "student", location = "/studentcommentpart.jsp"),
    @Result(name = "info", location = "/infocommentpart.jsp")
})
public class GetCommentBillAction extends SessionAction {

    @Resource
    IBillService billService;
    private String currentPage;
    private PageBean pb;
    private boolean isIndex;
    private String email;

    @Override
    public String execute() {
        String userType;
        if (isIndex) {
            email = (String) this.getSessionValue("email");
            userType = (String) this.getSessionValue("userType");
        } else {
            userType = "teacher";
        }
        int cp = Integer.parseInt(currentPage);
        int pageSize = 20;
        List<Bill> lc = billService.getCommentBillList(email, userType, (cp - 1) * pageSize, pageSize);
        int num = billService.getCommentNum(email);
        pb = new PageBean(lc, num, cp, pageSize);
        if (!isIndex) {
            return "info";
        }
        if (userType.equals("teacher")) {
            return "teacher";
        } else {
            return "student";
        }
    }

    public void setBillService(IBillService billService) {
        this.billService = billService;
    }

    public String getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(String currentPage) {
        this.currentPage = currentPage;
    }

    public PageBean getPb() {
        return pb;
    }

    public void setPb(PageBean pb) {
        this.pb = pb;
    }

    public boolean isIsIndex() {
        return isIndex;
    }

    public void setIsIndex(boolean isIndex) {
        this.isIndex = isIndex;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
