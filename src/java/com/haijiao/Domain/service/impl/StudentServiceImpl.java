/**
 *
 * @author Jerry Zou
 */

package com.haijiao.Domain.service.impl;
import com.haijiao.Domain.bean.Student;
import com.haijiao.Domain.service.IStudentService;

public class StudentServiceImpl implements IStudentService{ 

    @Override
    public boolean addStudent(String account, String password) {
        return true;
    }

    @Override
    public boolean confirmLogin(String account, String password) {
        if(account.equalsIgnoreCase("TEST") && password.equals("123456")){
            return true;
        }
        else
            return false;
    }

    @Override
    public Student getStudentById(int userId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
