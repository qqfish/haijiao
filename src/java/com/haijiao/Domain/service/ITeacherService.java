/**
 *
 * @author Jerry
 */
package com.haijiao.Domain.service;
import com.haijiao.Domain.bean.Schedule;
import com.haijiao.Domain.bean.Teacher;
import com.haijiao.Domain.bean.Timeslice;
import com.haijiao.Domain.bean.User;

public interface ITeacherService {
    //验证登录
    public boolean confirmLogin(String account, String password);
    public Teacher getTeacherById(int userId);
    //对某用户进行评论
    public boolean comment(User commenter, User commentee, String content, Integer score);
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
    
    //以及文件系统相关操作....
}
