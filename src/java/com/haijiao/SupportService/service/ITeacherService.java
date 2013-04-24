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
    public boolean changeInfo(String email, String name, String sex, Date birthday, String school, String tel, String province);
    //获取报酬
    public boolean takeMoney(String email, int numberOfCoin);
    //设置试听
    public boolean changeAudition(String email);
    
    //获取老师的课程
    public List<Clazz> getClasses(String email);
    //获取老师时间表
    public List<FreeTime> getSchedule(String email);
    //修改时间表
    //public boolean changeSchedule(String email, Schedule s);
}
