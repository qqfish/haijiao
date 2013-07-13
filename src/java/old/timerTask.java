/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package com.haijiao.global;
//
//import com.haijiao.SupportService.SpringContext;
//import com.haijiao.SupportService.service.IClassService;
//import java.util.TimerTask;
//
///**
// *
// * @author fish
// */
//public class timerTask extends TimerTask {
//
//    private int nowDay;
//    IClassService classService;
//    
//    public timerTask(){
//        classService = (IClassService) SpringContext.getContext().getBean("classServiceImpl");
//    }
//
//    @Override
//    public void run() {
//        classService.finishDay(nowDay);
//        nowDay = (nowDay + 1) % 7;
//    }
//
//    public int getNowDay() {
//        return nowDay;
//    }
//
//    public void setNowDay(int nowDay) {
//        this.nowDay = nowDay;
//    }
//
//    public IClassService getClassService() {
//        return classService;
//    }
//
//    public void setClassService(IClassService classService) {
//        this.classService = classService;
//    }
//    
//}
