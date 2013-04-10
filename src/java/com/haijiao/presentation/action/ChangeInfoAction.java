/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.Student;
import com.haijiao.Domain.bean.Teacher;
import com.haijiao.Domain.bean.User;
import com.haijiao.SupportService.service.IStudentService;
import com.haijiao.SupportService.service.ITeacherService;
import com.haijiao.SupportService.service.IUserService;
import java.sql.Date;

public class ChangeInfoAction extends SessionAction {
    ITeacherService teacherService;
    IStudentService studentService;
    IUserService userService;
    String password;
    String name;
    String sex;
    Date birthday;
    String school;
    String grade;
    String tel;
    String telType;
    String oldpwd;
    String newpwd;
    String newpwd2;
    
    public String teacherRegister(){
        if(teacherService.changeInfo((String)this.getValue("email"), name, sex, null, school, tel)){
            Teacher theTeacher = teacherService.getTeacherByEmail((String)this.getValue("email"));
            this.putIn("teacher", theTeacher);
            this.putIn("message", this.getText("teaRegisterSuccess"));
            return SUCCESS;
        } else {
            this.putIn("message", this.getText("teaRegisterFailure"));
            return "input";
        }
    }
    
    public String studentRegister(){
        if(studentService.changeInfo((String)this.getValue("email"), name, sex, null, grade, null, tel, null)){
            Student s = studentService.getStudentByEmail((String)this.getValue("email"));
            this.putIn("student", s);
            this.putIn("message", this.getText("stuRegisterSuccess"));
            return SUCCESS;
        } else {
            this.putIn("message", this.getText("stuRegisterFailure"));
            return "input";
        }
    }
    
    public String teacherChange(){
        if(teacherService.changeInfo((String)this.getValue("email"), name, sex, null, school, tel)){
            Teacher theTeacher = teacherService.getTeacherByEmail((String)this.getValue("email"));
            this.putIn("teacher", theTeacher);
            this.putIn("message", this.getText("teaChangeSuccess"));
            return SUCCESS;
        } else {
            this.putIn("message", this.getText("teaChangeFailure"));
            return "input";
        }
    }
    
    public String studentChange(){
        if(studentService.changeInfo((String)this.getValue("email"), name, sex, null, grade, null, tel, null)){
            Student s = studentService.getStudentByEmail((String)this.getValue("email"));
            this.putIn("student", s);
            this.putIn("message", this.getText("stuChangeSuccess"));
            return SUCCESS;
        } else {
            this.putIn("message", this.getText("stuChangeFailure"));
            return "input";
        }
    }
    
    public String changePassword(){
        User u = (User)this.getValue("user");
        System.out.println(u.getPassword());
        if ( !oldpwd.equals(u.getPassword())) {
            this.putIn("message", this.getText("passwordWrong"));
            return "input";
        } 
        if ( !newpwd.equals(newpwd2) ) {
            this.putIn("message", this.getText("passwordNotEqual"));
            return "input";
        }
        userService.changePassword( u.getEmail(), newpwd );
        this.putIn("message", this.getText("changePasswordSuccess"));
        return SUCCESS;
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

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

    public String getOldpwd() {
        return oldpwd;
    }

    public void setOldpwd(String oldpwd) {
        this.oldpwd = oldpwd;
    }

    public String getNewpwd() {
        return newpwd;
    }

    public void setNewpwd(String newpwd) {
        this.newpwd = newpwd;
    }

    public String getNewpwd2() {
        return newpwd2;
    }

    public void setNewpwd2(String newpwd2) {
        this.newpwd2 = newpwd2;
    }
}
