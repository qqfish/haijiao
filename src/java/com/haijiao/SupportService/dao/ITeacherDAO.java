/**
 *
 * @author Jerry
 */
package com.haijiao.SupportService.dao;
import com.haijiao.Domain.bean.Clazz;
import com.haijiao.Domain.bean.Schedule;
import com.haijiao.Domain.bean.Teacher;
import java.util.List;

public interface ITeacherDAO extends GenericDAO<Teacher,Integer> {
    public Teacher getTeacherByEmail(String email);
    
    //注册成为老师
    public boolean addTeacher(String email, String password, String school, String tel);
     //搜索老师（名字，年级，科目、网络状况）
    public List<Teacher> searchTeacher(List<String> strList);
    
    //获取报酬
    public boolean takeMoney(String email, int numberOfCoin);
    //修改基本资料
    public boolean changeInfo(String email, Teacher tc);
    //设置试听
    public boolean changeAudition(String email);
    
    //获取老师已完结课程
    public List<Clazz> getFinishedClasses(String email);
    //获取老师时间表
    public Schedule getSchedule(String email);
    //修改时间表
    public boolean changeSchedule(String email, Schedule s);
}
