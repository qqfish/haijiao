/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.haijiao.SupportService.service.ITeacherService;
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
@Action("setTeacherStatus")
@Results({
    @Result(name="success",type="redirect",location="index.action")
})
public class SetTeacherStatus extends SessionAction{
    @Resource
    ITeacherService teacherService;
    
    public String audition() {
        String email = (String) this.getSessionValue("email");
        teacherService.changeAudition(email);
        System.out.println("fff");
        return SUCCESS;
    }
    
    public String reserve() {
        String email = (String) this.getSessionValue("email");
        teacherService.changeReserve(email);
        return SUCCESS;
    }

    public ITeacherService getTeacherService() {
        return teacherService;
    }

    public void setTeacherService(ITeacherService teacherService) {
        this.teacherService = teacherService;
    }
}
