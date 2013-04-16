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

public class ChangeIntroAction extends SessionAction {
    IUserService userService;
    ITeacherService teacherService;
    IStudentService studentService;
    String intro;
    
    @Override
    public String execute(){
        if(userService.changeIntro((String)this.getValue("email"), intro)){
            if(((String)this.getValue("userType")).equals("teacher")){
                Teacher theTeacher = teacherService.getTeacherByEmail((String)this.getValue("email"));
                this.putIn("teacher", theTeacher);
            } else {
                Student theStudent = studentService.getStudentByEmail((String)this.getValue("email"));
                this.putIn("student", theStudent);
            }
            this.putIn("message", this.getText("changeIntroSuccess"));
            return SUCCESS;
        } else {
            this.putIn("message", this.getText("changeIntroFailure"));
            return "input";
        }
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }
}
