/**
 *
 * @author Jerry
 */
package com.haijiao.Domain.service;
import com.haijiao.Domain.bean.Clazz;
import com.haijiao.Domain.bean.Schedule;
import com.haijiao.Domain.bean.Student;
import com.haijiao.Domain.bean.Teacher;
import java.util.List;


public interface IStudentService extends Generic<Student,Integer> {
    public Student getStudentByAccount(String account);
    
    //注册成为学生
    public boolean addStudent(String account, String password, String grade, String school, String tel, String telType);
    //充值
    public boolean topUpMoney(String username, int numberOfCoin);
    //修改基本资料
    public boolean changeInfo(String username, Teacher tc);
    
    //获取学生已完结课程
    public List<Clazz> getFinishedClasses(String username);
    //获取学生时间表
    public Schedule getSchedule(String username);
}
