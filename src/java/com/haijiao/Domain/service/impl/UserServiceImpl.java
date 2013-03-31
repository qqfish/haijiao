/**
 *
 * @author Jerry Zou
 */

package com.haijiao.Domain.service.impl;

import com.haijiao.Domain.bean.Comment;
import com.haijiao.Domain.bean.Teacher;
import com.haijiao.Domain.service.IUserService;
import com.haijiao.SupportService.dao.ITeacherDAO;
import com.haijiao.SupportService.dao.IUserDAO;
import java.util.List;

public class UserServiceImpl implements IUserService{
    IUserDAO userDAO;
    ITeacherDAO teacherDAO;

    @Override
    public boolean confirmExist(String email) {
        return userDAO.confirmExist(email);
    }

    @Override
    public String confirmLogin(String email, String password) {
        return userDAO.confirmLogin(email, password);
    }

    @Override
    public boolean register(String account, String password, String userType) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Teacher> searchTeacher(List<String> strList) {
        return teacherDAO.searchTeacher(strList);
    }

    @Override
    public List<Comment> getComment(String email) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean comment(String commenterEmail, String commenteeEmail, String content, Integer score) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean uploadFile(String account, Object file) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Object download(String fileuri) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public IUserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public ITeacherDAO getTeacherDAO() {
        return teacherDAO;
    }

    public void setTeacherDAO(ITeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }
}
