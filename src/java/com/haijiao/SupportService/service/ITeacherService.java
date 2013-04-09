/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.service;

import com.haijiao.Domain.bean.Clazz;
import com.haijiao.Domain.bean.Schedule;
import com.haijiao.Domain.bean.Teacher;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Jerry
 */
public interface ITeacherService {
    public Teacher getTeacherByEmail(String email);
    
    //修改密码
    public boolean changePassword(String email, String password);
    //修改基本资料
    public boolean changeInfo(String email, String name, String sex, Date birthday, String school, String tel);
    //获取报酬
    public boolean takeMoney(String email, int numberOfCoin);
    //设置试听
    public boolean changeAudition(String email);
    
    //获取老师已完结课程---->用于显示账单
    public List<Clazz> getFinishedClasses(String email);
    //获取老师时间表
    public Schedule getSchedule(String email);
    //修改时间表
    public boolean changeSchedule(String email, Schedule s);
}
