/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.Teacher;
import com.haijiao.SupportService.service.ITeacherService;
import java.util.HashMap;
import java.util.Map;
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
@Action(value="getTeacherStatus")
@Results({@Result(name="success",type="json")})
public class GetTeacherStatusAction extends SessionAction{
    @Resource
    private ITeacherService teacherService;
    Map<String,Object> info;
    
    @Override
    public String execute(){
        String email = (String)this.getSessionValue("email");
        Teacher t = teacherService.getTeacherByEmail(email);
        info = new HashMap();
        info.put("status", t.getStudentin());
        info.put("reserveNum", t.getNewReserveNum());
        return SUCCESS;
    }

    public void setTeacherService(ITeacherService teacherService) {
        this.teacherService = teacherService;
    }

    public Map<String, Object> getInfo() {
        return info;
    }

    public void setInfo(Map<String, Object> info) {
        this.info = info;
    }
   
}
