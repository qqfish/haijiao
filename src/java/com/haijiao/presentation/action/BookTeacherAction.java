/**
 *
 * @author Jerry Zou
 */

package com.haijiao.presentation.action;

import com.haijiao.Domain.service.IStudentService;
import java.sql.Date;

public class BookTeacherAction extends SessionAction {
    IStudentService studentService;
    String teacherEmail;
    String lessonName;
    Date date;
    Integer beginIndex;
    Integer endIndex;
    Integer week;
    String day;
    
    @Override
    public String execute(){
        if (studentService.bookTeacher(teacherEmail,(String)this.getValue("username"), lessonName, date, week, day, beginIndex, endIndex)) {
            this.putIn("message", this.getText("successMessage"));
            return SUCCESS;
        } else {
            this.putIn("message", this.getText("errorMessage"));
            return "fail";
        }
    }

    public IStudentService getStudentService() {
        return studentService;
    }

    public void setStudentService(IStudentService studentService) {
        this.studentService = studentService;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getBeginIndex() {
        return beginIndex;
    }

    public void setBeginIndex(Integer beginIndex) {
        this.beginIndex = beginIndex;
    }

    public Integer getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(Integer endIndex) {
        this.endIndex = endIndex;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

}
