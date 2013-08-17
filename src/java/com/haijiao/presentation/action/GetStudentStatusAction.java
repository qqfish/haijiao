/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.Student;
import com.haijiao.SupportService.service.IStudentService;
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
@Action(value="getStudentStatus")
@Results({@Result(name="success",type="json")})
public class GetStudentStatusAction extends SessionAction{
    @Resource
    private IStudentService studentService;
    Map<String,Object> info;
    
    @Override
    public String execute(){
        String email = (String)this.getSessionValue("email");
        Student stu = studentService.getStudentByEmail(email);
        info = new HashMap();
        info.put("undealBill", stu.getUndealBill());
        return SUCCESS;
    }

    public IStudentService getStudentService() {
        return studentService;
    }

    public void setStudentService(IStudentService studentService) {
        this.studentService = studentService;
    }


    public Map<String, Object> getInfo() {
        return info;
    }

    public void setInfo(Map<String, Object> info) {
        this.info = info;
    }
   
}
