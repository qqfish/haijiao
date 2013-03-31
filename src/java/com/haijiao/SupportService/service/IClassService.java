/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.service;

import java.sql.Date;

/**
 *
 * @author hp
 */
public interface IClassService {
    //预定老师
    public boolean bookTeacher(String teacherEmail, String studentEmail, String lesson, Date date, Integer week, String day, Integer beginIndex, Integer endIndex);
    //处理学生预约
    public boolean dealWithReservation(int clazzId, boolean accept);
}
