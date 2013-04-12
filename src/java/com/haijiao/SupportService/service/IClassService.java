/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.service;

import com.haijiao.Domain.bean.Clazz;
import java.util.List;

/**
 *
 * @author hp
 */
public interface IClassService {
    //预定老师
    public boolean bookStableTeacher(String teacherEmail, String studentEmail, String lesson, Integer day, Integer index);
    
    public boolean bookTmpTeacher(String teacherEmail, String studentEmail, String lesson, int day, int index);
    
    public boolean studentPauseBook(String teacherEmail, String studentEmail, int day, int index);
    
    public boolean teacherPauseBook(String teacherEmail, String studentEmail, int day, int index);
    
    public boolean teacherAddClazz(String teacherEmail, int day, int index);
    
    public boolean teacherRemoveClazz(String teacherEmail, int day, int index);
    //处理学生预约
    public boolean dealWithReservation(int clazzId, boolean accept);
    //取消预约
    public boolean cancelBook(String teacherEmail, String studentEmail, int day, int index);
    
    public List<Clazz> getStudentClazz(String studentEmail);
    
    
}
