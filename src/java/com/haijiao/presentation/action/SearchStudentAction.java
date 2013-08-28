/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.Student;
import com.haijiao.SupportService.service.IStudentService;
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
@Action("searchStudent")
@Results({
    @Result(name="success",location="/teachers.jsp"),
    @Result(name="input",location="/teachers.jsp"),
    @Result(name="dynamic",location="/teacherspart.jsp")
})
public class SearchStudentAction extends RequestSessionAction{
    @Resource
    IStudentService studentService;
    private String searchContent;
    private String currentPage;
    private PageBean pb;
    private Integer desc;
    private String lesson;
    private String demand;
    private String address;
    private String way;
    private int duration;
    private int total;
    
    @Override
    public String execute() {
        int cp;
        String returnValue;
        if (currentPage == null) {
            cp = 1;
            returnValue = SUCCESS;
        } else if(currentPage.equals("")){
            cp = 1;
            returnValue = "dynamic";
        }
        else {
            cp = Integer.parseInt(currentPage);
            returnValue = "dynamic";
        }
        int pageSize = 20;
        List<Student> studentlist = null;
        int num = 0;
        pb = new PageBean(studentlist, num, cp, pageSize);
        if (studentlist == null || studentlist.isEmpty()) {
            this.sessionPutIn("message", this.getText("searchNull"));
        } else {
            this.sessionPutIn("message", this.getText("searchSuccess"));
        }
        return returnValue;
    }
    
}
