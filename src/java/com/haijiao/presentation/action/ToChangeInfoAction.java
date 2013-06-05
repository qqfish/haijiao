/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.Student;
import com.haijiao.Domain.bean.Teacher;
import com.haijiao.SupportService.service.IStudentService;
import com.haijiao.SupportService.service.ITeacherService;
import javax.annotation.Resource;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.InterceptorRefs;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

@Controller
@ParentPackage("haijiao")
@Namespace("/")
@InterceptorRefs({  
    @InterceptorRef("LoginCheckerStack")  
})  
@Action("toChangeInfo")
@Results({
    @Result(name="success",location="/changeInfo.jsp")
})
public class ToChangeInfoAction extends RequestSessionAction {
    @Resource
    private ITeacherService teacherService;
    @Resource
    private IStudentService studentService;
    private Teacher tea;
    private Student stu;
    private String nextPageMessage;
    
    @Override
    public String execute() {
        if ("teacher".equals((String)this.getOutSession("userType"))){
            tea = teacherService.getTeacherByEmail((String)this.getOutSession("email"));
        } else if ("student".equals((String)this.getOutSession("userType"))) {
            stu = studentService.getStudentByEmail((String)this.getOutSession("email"));
        } else {
            //return "failure";
        }
        if ("area".equals((String)this.getOutRequest("jump"))) {
            nextPageMessage = "您可以在下方输入框输入您的线下授课区域";
        }
        return SUCCESS;
    }

    public ITeacherService getTeacherService() {
        return teacherService;
    }

    public void setTeacherService(ITeacherService teacherService) {
        this.teacherService = teacherService;
    }

    public IStudentService getStudentService() {
        return studentService;
    }

    public void setStudentService(IStudentService studentService) {
        this.studentService = studentService;
    }

    public Teacher getTea() {
        return tea;
    }

    public void setTea(Teacher tea) {
        this.tea = tea;
    }

    public Student getStu() {
        return stu;
    }

    public void setStu(Student stu) {
        this.stu = stu;
    }

    public String getNextPageMessage() {
        return nextPageMessage;
    }

    public void setNextPageMessage(String nextPageMessage) {
        this.nextPageMessage = nextPageMessage;
    }
}
