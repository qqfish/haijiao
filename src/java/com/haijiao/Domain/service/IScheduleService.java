/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.service;

import com.haijiao.Domain.bean.Schedule;

/**
 *
 * @author Jerry
 */
public interface IScheduleService {
    //获取学生时间表
    public Schedule getStudentSchedule(String username);
    //获取老师时间表
    public Schedule getTeacherSchedule(String username);
    //修改时间表
    public boolean changeSchedule(String username, Schedule s);
}
