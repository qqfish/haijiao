/**
 *
 * @author Jerry
 */
package com.haijiao.Domain.service;
import com.haijiao.Domain.bean.Clazz;
import com.haijiao.Domain.bean.Schedule;
import com.haijiao.Domain.bean.Teacher;
import java.util.List;

public interface ITeacherService extends Generic<Teacher,Integer> {
    public Teacher getTeacherByAccount(String account);
    
    //注册成为老师
    public boolean addTeacher(String account, String password, String school, String tel);
     //搜索老师（名字，年级，科目、网络状况）
    public List<Teacher> searchTeacher(List<String> strList);
    
    //获取报酬
    public boolean takeMoney(String username, int numberOfCoin);
    //修改基本资料
    public boolean changeInfo(String username, Teacher tc);
    //设置试听
    public boolean changeAudition(String username);
    
    //获取老师已完结课程
    public List<Clazz> getFinishedClasses(String username);
    //获取老师时间表
    public Schedule getSchedule(String username);
    //修改时间表
    public boolean changeSchedule(String username, Schedule s);
}
