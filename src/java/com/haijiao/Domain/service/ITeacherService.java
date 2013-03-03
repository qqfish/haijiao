/**
 *
 * @author Jerry
 */
package com.haijiao.Domain.service;
import com.haijiao.Domain.bean.Schedule;
import com.haijiao.Domain.bean.Teacher;
import com.haijiao.Domain.bean.Timeslice;
import com.haijiao.Domain.bean.User;

public interface ITeacherService extends IUserService {
    public Teacher getTeacherByAccount(String account);

    //获取报酬
    public boolean takeMoney(Teacher tc, int number);
    //修改空闲时刻表
    public boolean changeSchedule(Schedule s);
    //修改基本资料
    public boolean changeInfo(Teacher tc);
    //设置试听
    public boolean changeAudition(Teacher tc, boolean boo);
    //处理学生预约
    public boolean dealToReservation(Teacher tc, Timeslice ts);
}
