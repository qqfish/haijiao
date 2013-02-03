/**
 *
 * @author Jerry
 */
package com.haijiao.service;
import com.haijiao.persist.Student;

public interface IStudentService {
    public boolean addStudent(String account, String password);
    public boolean confirmLogin(String account, String password);
    public Student getStudentById(int userId);
}
