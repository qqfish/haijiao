/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.haijiao.SupportService.service.IStudentService;
import com.haijiao.SupportService.service.ITeacherService;
import java.sql.Date;

public class ChangeInfoAction extends SessionAction {
    ITeacherService teacherService;
    IStudentService studentService;
    String password;
    String name;
    String sex;
    Date birthday;
    String school;
    String grade;
    String tel;
    String telType;

    @Override
    public String execute(){
        if(teacherService.changeInfo((String)this.getValue("email"), name, sex, birthday, school, tel)){
            this.putIn("message", this.getText("successmessage"));
            return SUCCESS;
        } else {
            this.putIn("message", this.getText("errormessage"));
            return "unconnected";
        }
    }
    
    public String teacherRegister(){
        if(teacherService.changeInfo((String)this.getValue("email"), name, sex, null, school, tel)){
            this.putIn("message", this.getText("teaRegisterSuccess"));
            return SUCCESS;
        } else {
            this.putIn("message", this.getText("teaRegisterFailure"));
            return "input";
        }
    }
    
    public String studentRegister(){
        if(studentService.changeInfo((String)this.getValue("email"), name, sex, null, grade, null, tel, null)){
            this.putIn("message", this.getText("stuRegisterSuccess"));
            return SUCCESS;
        } else {
            this.putIn("message", this.getText("stuRegisterFailure"));
            return "input";
        }
    }

    public ITeacherService getTeacherService() {
        return teacherService;
    }

    public void setTeacherService(ITeacherService teacherService) {
        this.teacherService = teacherService;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public IStudentService getStudentService() {
        return studentService;
    }

    public void setStudentService(IStudentService studentService) {
        this.studentService = studentService;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getTelType() {
        return telType;
    }

    public void setTelType(String telType) {
        this.telType = telType;
    }
}
