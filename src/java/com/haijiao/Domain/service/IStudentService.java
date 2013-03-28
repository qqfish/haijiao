/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.service;

import com.haijiao.Domain.bean.Clazz;
import com.haijiao.Domain.bean.Schedule;
import com.haijiao.Domain.bean.Student;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Jerry
 */
public interface IStudentService {
    public Student getStudentByEmail(String email);
    
    //修改基本资料
    public boolean changeInfo(String email, String password, String name, String sex, Date birthday, String grade, String school, String tel, String telType);
    //充值
    public boolean topUpMoney(String email, int numberOfCoin);
    
    //获取学生已完结课程----->用于账单
    public List<Clazz> getFinishedClasses(String email);
    //获取学生时间表
    public Schedule getSchedule(String email);
    //预定老师
    public boolean bookTeacher(String teacherEmail, String studentEmail, String lesson, Date date, Integer week, String day, Integer beginIndex, Integer endIndex);
}
