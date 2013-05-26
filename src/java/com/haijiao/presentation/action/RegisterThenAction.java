/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.haijiao.Domain.bean.Student;
import com.haijiao.Domain.bean.Teacher;
import com.haijiao.SupportService.service.IStudentService;
import com.haijiao.SupportService.service.ITeacherService;

public class RegisterThenAction extends SessionAction {
    ITeacherService teacherService;
    IStudentService studentService;
    String id;
    String password;
    String name;
    String sex;
    String school;
    String major;
    String studyStatus;
    String grade;
    String province;
    String net;
    String nextPageMessage;
    
    public String teacherRegister(){
        if(teacherService.changeInfo((String)this.getSessionValue("email"), name, sex, null, school, major, studyStatus, null, province, net)){
            Teacher theTeacher = teacherService.getTeacherByEmail((String)this.getSessionValue("email"));
            this.sessionPutIn("name", theTeacher.getName());
            this.sessionPutIn("userType", "teacher");
            this.sessionPutIn("todayClazz", teacherService.getTodayClasses((String)this.getSessionValue("email")));
            return SUCCESS;
        } else {
            nextPageMessage = "注册信息提交失败！";
            return "input";
        }
    }
    
    public String studentRegister(){
        if(studentService.changeInfo((String)this.getSessionValue("email"), name, sex, null, grade, school, null, null)){
            Student s = studentService.getStudentByEmail((String)this.getSessionValue("email"));
            this.sessionPutIn("name", s.getName());
            this.sessionPutIn("userType", "student");
            this.sessionPutIn("todayClazz", studentService.getTodayClasses((String)this.getSessionValue("email")));
            return SUCCESS;
        } else {
            nextPageMessage = "注册信息提交失败！";
            return "input";
        }
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getStudyStatus() {
        return studyStatus;
    }

    public void setStudyStatus(String studyStatus) {
        this.studyStatus = studyStatus;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getNet() {
        return net;
    }

    public void setNet(String net) {
        this.net = net;
    }

    public String getNextPageMessage() {
        return nextPageMessage;
    }

    public void setNextPageMessage(String nextPageMessage) {
        this.nextPageMessage = nextPageMessage;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
    
}
