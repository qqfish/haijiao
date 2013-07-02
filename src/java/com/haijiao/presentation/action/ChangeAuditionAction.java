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
@Action("changeAudition")
@Results({
    @Result(name="success",type="chain",location="index")
})
public class ChangeAuditionAction extends SessionAction{
    @Resource
    ITeacherService teacherService;
    
    @Override
    public String execute() {
        String email = (String) this.getSessionValue("email");
        teacherService.changeAudition(email);
        return SUCCESS;
    }
}
