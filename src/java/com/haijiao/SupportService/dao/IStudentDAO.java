/**
 *
 * @author Jerry
 */
package com.haijiao.SupportService.dao;
import com.haijiao.Domain.bean.Clazz;
import com.haijiao.Domain.bean.Schedule;
import com.haijiao.Domain.bean.Student;
import com.haijiao.Domain.bean.Teacher;
import java.util.List;


public interface IStudentDAO extends GenericDAO<Student,Integer> {
    public Student getStudentByEmail(String email);
    
    //注册成为学生
    public boolean addStudent(String email, String password, String grade, String school, String tel, String telType);
    //充值
    public boolean topUpMoney(String email, int numberOfCoin);
    //修改基本资料
    public boolean changeInfo(String email, Teacher tc);
    
    //获取学生已完结课程
    public List<Clazz> getFinishedClasses(String email);
    //获取学生时间表
    public Schedule getSchedule(String email);
}
