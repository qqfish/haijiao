/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.Student;
import com.haijiao.Domain.bean.Teacher;
import com.haijiao.SupportService.service.IStudentService;
import com.haijiao.SupportService.service.ITeacherService;
import com.haijiao.SupportService.service.IUserService;
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
@Action("changeIntro")
@Results({
    @Result(name="input",location="/changeInfo.jsp"),
    @Result(name="success",type="chain",location="toChangeInfo")
})
public class ChangeIntroAction extends SessionAction {
    @Resource
    private IUserService userService;
    @Resource
    private ITeacherService teacherService;
    @Resource
    private IStudentService studentService;
    private String intro;
    private String nextPageMessage;
    
    @Override
    public String execute(){
        if(userService.changeIntro((String)this.getSessionValue("email"), intro)){
            if(((String)this.getSessionValue("userType")).equals("teacher")){
                Teacher theTeacher = teacherService.getTeacherByEmail((String)this.getSessionValue("email"));
                this.sessionPutIn("teacher", theTeacher);
            } else {
                Student theStudent = studentService.getStudentByEmail((String)this.getSessionValue("email"));
                this.sessionPutIn("student", theStudent);
            }
            nextPageMessage = "修改详细介绍成功！";
            return SUCCESS;
        } else {
            nextPageMessage = "修改详细介绍失败！";
            return "input";
        }
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public void setTeacherService(ITeacherService teacherService) {
        this.teacherService = teacherService;
    }

    public void setStudentService(IStudentService studentService) {
        this.studentService = studentService;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getNextPageMessage() {
        return nextPageMessage;
    }

    public void setNextPageMessage(String nextPageMessage) {
        this.nextPageMessage = nextPageMessage;
    }
}
