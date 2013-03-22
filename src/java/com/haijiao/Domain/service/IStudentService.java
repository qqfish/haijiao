/**
 *
 * @author Jerry
 */
package com.haijiao.Domain.service;
import com.haijiao.Domain.bean.Student;
import com.haijiao.Domain.bean.Timeslice;
import java.util.List;

public interface IStudentService extends Generic<Student,Integer> {
    public Student getStudentByAccount(String account);
    
    //预定老师
    public boolean bookTeacher(String studentAccount, String teacherAccount, List<Timeslice> timeslices);
}
