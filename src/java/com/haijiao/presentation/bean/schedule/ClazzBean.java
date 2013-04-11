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

    private String stableStudentEmail;
    
    private String tmpStudentEmail;

    private String lesson;

    private int day;   //星期X
    private int index;    //时间片index，比如 1对应 8:00-9:00，2对应 9:00-10:00

    private boolean studentPause;
    private boolean teacherPause;
    private boolean accept;  //本次预约老师是否已接受
    private String message;   //本次预约在接受或拒绝后附加的信息
    private Integer pay;    //本次课程的花费
    private boolean finish;  //本次课程是否已结束（结算）
    
    public ClazzBean(Clazz c){
//        if(c.getTeacher() != null)
//            teacherEmail = c.getTeacher().getEmail();
//        if(c.getStableStudent() != null)
//            stableStudentEmail = c.getStableStudent().getEmail();
//        if(c.getTmpStudent() != null)
//            tmpStudentEmail = c.getTmpStudent().getEmail();
//        if(c.getLesson() != null)
//            lesson = c.getLesson().getName();
//        day = c.getDay();
//        index = c.getIndex();
//        studentPause = c.isStudentPause();
//        teacherPause = c.isTeacherPause();
//        accept = c.isAccept();
//        message = c.getMessage();
//        pay = c.getPay();
//        finish = c.isFinish();
    }
    
    public ClazzBean() {
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public String getLesson() {
        return lesson;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
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

    public String getStableStudentEmail() {
        return stableStudentEmail;
    }

    public void setStableStudentEmail(String stableStudentEmail) {
        this.stableStudentEmail = stableStudentEmail;
    }

    public String getTmpStudentEmail() {
        return tmpStudentEmail;
    }

    public void setTmpStudentEmail(String tmpStudentEmail) {
        this.tmpStudentEmail = tmpStudentEmail;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isStudentPause() {
        return studentPause;
    }

    public void setStudentPause(boolean studentPause) {
        this.studentPause = studentPause;
    }

    public boolean isTeacherPause() {
        return teacherPause;
    }

    public void setTeacherPause(boolean teacherPause) {
        this.teacherPause = teacherPause;
    }
    
    
}
