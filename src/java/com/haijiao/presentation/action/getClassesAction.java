/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.Bill;
import com.haijiao.SupportService.service.IBillService;
import java.util.ArrayList;
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
@ParentPackage("json-default")
@Namespace("/")
@Action(value="getClasses")
@Results({@Result(name="success",type="json")})
public class GetClassesAction extends SessionAction{
    @Resource
    private IBillService billService;
    List<String> name;
    List<String> lesson;
    List<String> teaEmail;
    List<String> stuEmail;
    int classNum;
    
    @Override
    public String execute(){
        String email = (String)this.getSessionValue("email");
        String userType = (String) this.getSessionValue("userType");
        List<Bill> bills = billService.getUnfinishedBill(email, userType);
        classNum = bills.size();
        name = new ArrayList();
        lesson = new ArrayList();
        teaEmail = new ArrayList();
        stuEmail = new ArrayList();
        for(int i =0;i < classNum; i ++){
            name.add(bills.get(i).getStudent().getName());
            lesson.add(bills.get(i).getLesson());
            teaEmail.add(bills.get(i).getTeacher().getEmail());
            stuEmail.add(bills.get(i).getStudent().getEmail());
        }
        return SUCCESS;
    }

    public void setBillService(IBillService billService) {
        this.billService = billService;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public List<String> getLesson() {
        return lesson;
    }

    public void setLesson(List<String> lesson) {
        this.lesson = lesson;
    }

    public int getClassNum() {
        return classNum;
    }

    public void setClassNum(int classNum) {
        this.classNum = classNum;
    }

    public List<String> getTeaEmail() {
        return teaEmail;
    }

    public void setTeaEmail(List<String> teaEmail) {
        this.teaEmail = teaEmail;
    }

    public List<String> getStuEmail() {
        return stuEmail;
    }

    public void setStuEmail(List<String> stuEmail) {
        this.stuEmail = stuEmail;
    }
    
}