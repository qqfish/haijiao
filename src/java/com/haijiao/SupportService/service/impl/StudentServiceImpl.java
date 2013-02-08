/**
 *
 * @author Jerry Zou
 */

package com.haijiao.SupportService.service.impl;
import com.haijiao.SupportService.dao.impl.StudentDAOImpl;
import com.haijiao.persist.Student;
import com.haijiao.SupportService.service.IStudentService;

public class StudentServiceImpl implements IStudentService{

    private StudentDAOImpl studentImpl;

    public void setStudentImpl(StudentDAOImpl studentImpl) {
        this.studentImpl = studentImpl;
    }
    
    @Override
    public boolean addStudent(String account, String password) {
        Student s = new Student();
        s.setAccount(account);
        s.setPassword(password);
        return studentImpl.makePersistent(s);
    }

    @Override
    public Student getStudentById(int userId) {
        return studentImpl.findById(userId);
    }

    @Override
    public boolean confirmLogin(String account, String password) {
        return studentImpl.confirm(account, password);
    }
    
}
