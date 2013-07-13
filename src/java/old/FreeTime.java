/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package com.haijiao.Domain.bean;
//
//import java.util.ArrayList;
//import java.util.List;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.OneToMany;
//import javax.persistence.Table;
//
///**
// *
// * @author fish
// */
//@Entity
//@Table
//public class FreeTime extends BaseBean{
//    
//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name="tid")
//    private Teacher teacher;
//    
//    @Column(name="weekday")
//    private int weekday;   //星期X 从0到6
//    
//    @Column(name="sliceindex")
//    private int sliceIndex;    //时间片index，比如 1对应 8:00-9:00，2对应 9:00-10:00
//    
//    @OneToMany(mappedBy = "freeTime")
//    private List<Clazz> clazzList;  //该空闲时间选的课程，第一个为本次的课程，完成后第一个clazz的remain减1，为0则删除, timeToClass 也减一
//
//    public String strWeekday(){
//        switch(weekday) {
//            case 0:
//                return "周一";
//            case 1:
//                return "周二";
//            case 2:
//                return "周三";
//            case 3:
//                return "周四";
//            case 4:
//                return "周五";
//            case 5:
//                return "周六";
//            case 6:
//                return "周日";
//            default:
//                return "周？";
//        }
//    }
//    
//    public String strSliceIndex(){
//        switch(sliceIndex) {
//            case 0:
//                return "8:00-9:00";
//            case 1:
//                return "9:00-10:00";
//            case 2:
//                return "10:00-11:00";
//            case 3:
//                return "11:00-12:00";
//            case 4:
//                return "12:00-13:00";
//            case 5:
//                return "13:00-14:00";
//            case 6:
//                return "14:00-15:00";
//            case 7:
//                return "15:00-16:00";
//            case 8:
//                return "16:00-17:00";
//            case 9:
//                return "17:00-18:00";
//            case 10:
//                return "18:00-19:00";
//            case 11:
//                return "19:00-20:00";
//            default:
//                return "??:00-??:00";
//        }
//    }
//    
//    public FreeTime() {
//        clazzList = new ArrayList();
//    }
//
//    public Teacher getTeacher() {
//        return teacher;
//    }
//
//    public void setTeacher(Teacher teacher) {
//        this.teacher = teacher;
//    }
//
//    public int getWeekday() {
//        return weekday;
//    }
//
//    public void setWeekday(int weekday) {
//        this.weekday = weekday;
//    }
//
//    public int getSliceIndex() {
//        return sliceIndex;
//    }
//
//    public void setSliceIndex(int sliceIndex) {
//        this.sliceIndex = sliceIndex;
//    }
//
//    public List<Clazz> getClazzList() {
//        return clazzList;
//    }
//
//    public void setClazzList(List<Clazz> clazzList) {
//        this.clazzList = clazzList;
//    }
//}
