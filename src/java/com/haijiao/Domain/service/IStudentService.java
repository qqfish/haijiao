/**
 *
 * @author Jerry
 */
package com.haijiao.Domain.service;
import com.haijiao.Domain.bean.Student;

public interface IStudentService {
    public boolean addStudent(String account, String password);
    public boolean confirmLogin(String account, String password);
    public Student getStudentById(int userId);
}
