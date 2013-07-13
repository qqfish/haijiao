/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package com.haijiao.presentation.bean.schedule;
//
//import com.haijiao.Domain.bean.Clazz;
//import com.haijiao.Domain.bean.FreeTime;
//
///**
// *
// * @author fish
// */
//public class FreeTimeBean {
//
//    private String teacherEmail;
//
//    private String studentEmail;
//
//    private String lesson;
//
//    private int day;   //星期X
//    private int index;    //时间片index，比如 1对应 8:00-9:00，2对应 9:00-10:00
//    
//    private int status;  //本次预约的状态，可选值未Status类中
//    private int remain;    //课程次数，-1为无限,每上一次减1，到0该课程被清除
//    
//    public FreeTimeBean(FreeTime f){
//        super();
//        Clazz first = f.getClazzList().get(0);
//        if(first.getFreeTime().getTeacher() != null)
//            teacherEmail = first.getFreeTime().getTeacher().getEmail();
//        if(first.getStudent() != null)
//            studentEmail = first.getStudent().getEmail();
//        if(first.getLesson() != null)
//            lesson = first.getLesson().getName();
//        
//        day = first.getFreeTime().getWeekday();
//        index = first.getFreeTime().getSliceIndex();
//        status = first.getStatus();
//        remain = first.getRemain();
//    }
//    
//    public FreeTimeBean(Clazz first){
//        super();
//        if(first.getFreeTime().getTeacher() != null)
//            teacherEmail = first.getFreeTime().getTeacher().getEmail();
//        if(first.getStudent() != null)
//            studentEmail = first.getStudent().getEmail();
//        if(first.getLesson() != null)
//            lesson = first.getLesson().getName();
//        
//        day = first.getFreeTime().getWeekday();
//        index = first.getFreeTime().getSliceIndex();
//        status = first.getStatus();
//        remain = first.getRemain();
//    }
//    
//    public FreeTimeBean() {
//    }
//
//    public String getTeacherEmail() {
//        return teacherEmail;
//    }
//
//    public void setTeacherEmail(String teacherEmail) {
//        this.teacherEmail = teacherEmail;
//    }
//
//    public String getLesson() {
//        return lesson;
//    }
//
//    public void setLesson(String lesson) {
//        this.lesson = lesson;
//    }
//
//    public int getDay() {
//        return day;
//    }
//
//    public void setDay(int day) {
//        this.day = day;
//    }
//
//    public int getIndex() {
//        return index;
//    }
//
//    public void setIndex(int index) {
//        this.index = index;
//    }
//
//    public String getStudentEmail() {
//        return studentEmail;
//    }
//
//    public void setStudentEmail(String studentEmail) {
//        this.studentEmail = studentEmail;
//    }
//
//    public int getStatus() {
//        return status;
//    }
//
//    public void setStatus(int status) {
//        this.status = status;
//    }
//
//    public int getRemain() {
//        return remain;
//    }
//
//    public void setRemain(int remain) {
//        this.remain = remain;
//    }
//
//    @Override
//    public int hashCode() {
//        int hash = 5;
//        hash = 31 * hash + this.day;
//        hash = 31 * hash + this.index;
//        return hash;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final FreeTimeBean other = (FreeTimeBean) obj;
//        if (this.day != other.day) {
//            return false;
//        }
//        if (this.index != other.index) {
//            return false;
//        }
//        return true;
//    }
//
//}
