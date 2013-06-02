/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.service;

import com.haijiao.Domain.bean.Clazz;
import com.haijiao.Domain.bean.FreeTime;
import com.haijiao.Domain.bean.Teacher;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Jerry
 */
public interface ITeacherService {
    public Teacher getTeacherByEmail(String email);
    
    //修改基本资料
    public boolean changeInfo(String email, String name, String sex, Date birthday, String school, String major,
                String tel, String studyStatus, String province, String city, String district, String net);
    
    public boolean changeMoreInfo(String email, String underlineArea, String experience, Boolean sprtUnderline, Boolean sprtOnline);
    //获取报酬
    public boolean takeMoney(String email, int numberOfCoin);
    //设置试听
    public boolean changeAudition(String email);
    
    public boolean increseObNum(String email);
    
    public boolean addLesson(String email, String lessonName);
    
    public boolean deleteLesson(String email, String lessonName);
    
    public boolean setRoomOccupied(String email, String stuEmail);
    
    public String getRoomOccupied(String email);
    
    //获取老师的课程
    public List<Clazz> getClasses(String email);
    //获取老师当天课程
    public List<Clazz> getTodayClasses(String email);
    //获取老师时间表
    public List<FreeTime> getSchedule(String email);
    //修改时间表
    //public boolean changeSchedule(String email, Schedule s);
}
