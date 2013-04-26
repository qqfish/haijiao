/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.Student;
import com.haijiao.Domain.bean.Teacher;
import com.haijiao.SupportService.service.IStudentService;
import com.haijiao.SupportService.service.ITeacherService;

public class ToChangeInfoAction extends SessionAction {
    ITeacherService teacherService;
    IStudentService studentService;
    Teacher tea;
    Student stu;
    
    @Override
    public String execute() {
        if ("teacher".equals((String)this.getSessionValue("userType"))){
            tea = teacherService.getTeacherByEmail((String)this.getSessionValue("email"));
        } else if ("student".equals((String)this.getSessionValue("userType"))) {
        stu = studentService.getStudentByEmail((String)this.getSessionValue("email"));
        } else {
            //return "failure";
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
}
