/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.Teacher;
import com.haijiao.SupportService.service.ITeacherService;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.stereotype.Controller;

/**
 *
 * @author hp
 */
@Controller
@ParentPackage("json-default")
@Namespace("/")
@Results({@Result(name="success",type="json")})
public class GetTeacherStatusAction extends SessionAction{
    @Resource
    private ITeacherService teacherService;
    Map<String,Object> info;
    
    @Action(value="getTeacherStatus")
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
