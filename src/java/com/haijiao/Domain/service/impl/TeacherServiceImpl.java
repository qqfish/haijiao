/**
 *
 * @author Jerry Zou
 */

package com.haijiao.Domain.service.impl;
import com.haijiao.SupportService.dao.impl.TeacherDAOImpl;
import com.haijiao.persist.Teacher;
import com.haijiao.Domain.service.ITeacherService;

public class TeacherServiceImpl implements ITeacherService{
    private TeacherDAOImpl teacherImpl;

    @Override
    public boolean confirmLogin(String account, String password) {
        return teacherImpl.confirm(account, password);
    }

    @Override
    public Teacher getTeacherById(int userId) {
        return teacherImpl.findById(userId);
    }

}
