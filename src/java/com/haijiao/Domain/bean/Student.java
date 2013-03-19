/**
 *
 * @author fish
 */

package com.haijiao.Domain.bean;

import java.util.ArrayList;
import java.util.List;

public class Student extends User{
    private String grade;   //学生当前的年级
    private String school;  //学生就读学校
    private String tel;        //学生或家长的联系方式
    private String telType; //="student" or "parent"
    private Schedule schedule;      //学生的时间表
    private List<Teacher> teacherList;   //预约过的老师列表
    private List<Clazz> classList; //预约好的课程列表
    private List<Clazz> classFinishedList; //已结束的课程列表

    public Student() {
        this.teacherList = new ArrayList<Teacher>();
        this.classList = new ArrayList<Clazz>();
        this.classFinishedList = new ArrayList<Clazz>();
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
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

    public String getTelType() {
        return telType;
    }

    public void setTelType(String telType) {
        this.telType = telType;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public List<Teacher> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<Teacher> teacherList) {
        this.teacherList = teacherList;
    }

    public List<Clazz> getClassList() {
        return classList;
    }

    public void setClassList(List<Clazz> classList) {
        this.classList = classList;
    }

    public List<Clazz> getClassFinishedList() {
        return classFinishedList;
    }

    public void setClassFinishedList(List<Clazz> classFinishedList) {
        this.classFinishedList = classFinishedList;
    }
}
