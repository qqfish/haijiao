/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package com.haijiao.presentation.bean.infoSchedule;
//
//import com.haijiao.Domain.bean.Clazz;
//import com.haijiao.Domain.bean.FreeTime;
//
///**
// *
// * @author fish
// */
//public class InfoFreeTimeBean {
//
//    private int day;   //星期X
//    private int index;    //时间片index，比如 1对应 8:00-9:00，2对应 9:00-10:00
//    
//    private int status;  //本次预约的状态，可选值未Status类中
//    private int remain;    //课程次数，-1为无限,每上一次减1，到0该课程被清除
//    
//    public InfoFreeTimeBean(FreeTime f){
//        Clazz first = f.getClazzList().get(0);
//        
//        day = first.getFreeTime().getWeekday();
//        index = first.getFreeTime().getSliceIndex();
//        status = first.getStatus();
//        remain = first.getRemain();
//    }
//    
//    public InfoFreeTimeBean(Clazz first){
//        
//        day = first.getFreeTime().getWeekday();
//        index = first.getFreeTime().getSliceIndex();
//        status = first.getStatus();
//        remain = first.getRemain();
//    }
//    
//    public InfoFreeTimeBean() {
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
//        final InfoFreeTimeBean other = (InfoFreeTimeBean) obj;
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
