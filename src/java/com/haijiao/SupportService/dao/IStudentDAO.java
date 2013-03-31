/**
 *
 * @author Jerry
 */
package com.haijiao.SupportService.dao;
import com.haijiao.Domain.bean.Student;


public interface IStudentDAO extends GenericDAO<Student,Integer> {
    public Student getStudentByEmail(String email);
    
    //注册成为学生
    public boolean addStudent(String email, String password, String grade, String school, String tel, String telType);
}
