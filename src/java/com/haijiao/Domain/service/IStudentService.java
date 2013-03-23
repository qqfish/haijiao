/**
 *
 * @author Jerry
 */
package com.haijiao.Domain.service;
import com.haijiao.Domain.bean.Clazz;
import com.haijiao.Domain.bean.Schedule;
import com.haijiao.Domain.bean.Student;
import com.haijiao.Domain.bean.Timeslice;
import java.util.List;

public interface IStudentService extends Generic<Student,Integer> {
    public Student getStudentByAccount(String account);
    
    //获取老师时间表
    public Schedule getStudentSchedule(String username);
    //获取老师已完结课程
    public List<Clazz> getStudentFinishedClasses(String username);
    
    //预定老师
    public boolean bookTeacher(String studentAccount, String teacherAccount, List<Timeslice> timeslices);
}
