/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//package com.haijiao.SupportService.service;
//
//import com.haijiao.Domain.bean.Clazz;
//import com.haijiao.global.scheduleLocation;
//import java.util.List;
//
///**
// *
// * @author hp
// */
//public interface IClassService {
//    //预定老师
//    public boolean bookTeacher(String teacherEmail, String studentEmail, String lesson, List<scheduleLocation> cList, int num);
//    
//    public boolean studentPauseBook(int clazzId, int num);
//    
//    public boolean teacherPauseBook(int clazzId, int num);
//    
//    public boolean teacherCancelBook(int clazzId, int num);
//    
//    public boolean teacherAddClazz(String teacherEmail, List<scheduleLocation> cList);
//    
//    public boolean teacherRemoveClazz(String teacherEmail, List<scheduleLocation> cList);
//    //处理学生预约
//    public boolean dealWithReservation(int clazzId, boolean accept);
//    //取消预约
//    public boolean cancelBook(int clazzId);
//    
//    public Clazz getClazzById(int clazzId);
//    
//    public boolean finishDay(int day);
//    
//}
