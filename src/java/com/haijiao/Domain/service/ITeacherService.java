/**
 *
 * @author Jerry
 */
package com.haijiao.Domain.service;
import com.haijiao.Domain.bean.Clazz;
import com.haijiao.Domain.bean.Schedule;
import com.haijiao.Domain.bean.Teacher;

public interface ITeacherService extends Generic<Teacher,Integer> {
    public Teacher getTeacherByAccount(String account);

    //获取报酬
    public boolean takeMoney(String username, int numberOfCoin);
    //修改空闲时刻表
    public boolean changeSchedule(String username, Schedule s);
    //修改基本资料
    public boolean changeInfo(String username, Teacher tc);
    //设置试听
    public boolean changeAudition(String username);
    //处理学生预约
    public boolean dealWithReservation(String username, Clazz c, boolean accept);
}
