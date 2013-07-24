/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.haijiao.SupportService.service.IBillService;
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
@Action("bookTeacher")
@Results({
    @Result(name="success",type="redirect",location="getTeacherInfo.action?teacherEmail=${teacherEmail}")
})
public class BookTeacherAction extends SessionAction{
    @Resource
    private IBillService billService;
    @Resource
    private IMailService mailService;
    private String teacherEmail;
    private int duration;
    private String lessonName;
    private String type;
    private String message;
    
    @Override
    public String execute() {
        billService.produceBill((String) this.getSessionValue("email"), teacherEmail, duration, lessonName, type.concat(message));
        this.sessionPutIn("nextPageMessage", "预约已成功。\n在老师确认预约以后，你会接到通知。\n");
        return SUCCESS;
    }

    public void setBillService(IBillService billService) {
        this.billService = billService;
    }

    public void setMailService(IMailService mailService) {
        this.mailService = mailService;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    } 
}
