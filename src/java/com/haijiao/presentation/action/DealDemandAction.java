/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.haijiao.SupportService.service.IStudentService;
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
@Action("dealDemand")
@Results({
    @Result(name="input",type="redirect",location="index.action"),
    @Result(name="success",type="redirect",location="index.action")
})
public class DealDemandAction extends SessionAction{
    @Resource
    private IStudentService studentService;
    private String lesson;
    private String demand;
    private String address;
    private String way;
    private int duration;
    private int total;
    
    public String changeDemand(){
        if(studentService.changeDemand((String)this.getSessionValue("email"), lesson, demand, way, address, duration, total)){
            this.sessionPutIn("nextPageMessage", "修改成功");
            return SUCCESS;
        }
        else{
            this.sessionPutIn("nextPageMessage", "修改失败");
            return INPUT;
        }
    }
    
    public String publishDemand(){
        if(studentService.publishDemand((String)this.getSessionValue("email"), lesson, demand, way, address, duration, total)){
            this.sessionPutIn("nextPageMessage", "发布成功");
            return SUCCESS;
        }
        else{
            this.sessionPutIn("nextPageMessage", "发布失败");
            return INPUT;
        }
    }
    
    public String cancelDemand(){
        if(studentService.cancelDemand((String)this.getSessionValue("email"))){
            this.sessionPutIn("nextPageMessage", "取消成功");
            return SUCCESS;
        }
        else{
            this.sessionPutIn("nextPageMessage", "取消失败");
            return INPUT;
        }
    }

    public void setStudentService(IStudentService studentService) {
        this.studentService = studentService;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
}
