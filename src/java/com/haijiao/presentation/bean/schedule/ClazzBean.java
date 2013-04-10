/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.presentation.bean.schedule;

import com.haijiao.Domain.bean.Clazz;
import java.sql.Date;

/**
 *
 * @author fish
 */
public class ClazzBean {

    private String teacherEmail;

    private String studentEmail;

    private String lesson;

    private Date date;   //日期
    
    private Integer week; //第一/二个星期，此处只能取值1或2

    private int day;   //星期X
    private int beginIndex;    //时间片index，比如 1对应 8:00-9:00，2对应 9:00-10:00
    private int endIndex;
    
    private boolean accept;  //本次预约老师是否已接受
    private String message;   //本次预约在接受或拒绝后附加的信息
    private Integer pay;    //本次课程的花费
    private boolean finish;  //本次课程是否已结束（结算）
    
    public ClazzBean(Clazz c){
        if(c.getTeacher() != null)
            teacherEmail = c.getTeacher().getEmail();
        if(c.getStudent() != null)
            studentEmail = c.getStudent().getEmail();
        if(c.getLesson() != null)
            lesson = c.getLesson().getName();
        date = c.getDate();
        week = c.getWeek();
        day = c.getDay();
        beginIndex = c.getBeginIndex();
        endIndex = c.getEndIndex();
        accept = c.isAccept();
        message = c.getMessage();
        pay = c.getPay();
        finish = c.isFinish();
    }
    
    public ClazzBean() {
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getBeginIndex() {
        return beginIndex;
    }

    public void setBeginIndex(int beginIndex) {
        this.beginIndex = beginIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getPay() {
        return pay;
    }

    public void setPay(Integer pay) {
        this.pay = pay;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }
    
    
}
