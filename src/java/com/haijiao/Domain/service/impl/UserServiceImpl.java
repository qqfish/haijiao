/**
 *
 * @author Jerry Zou
 */

package com.haijiao.Domain.service.impl;

import com.haijiao.Domain.bean.Comment;
import com.haijiao.Domain.bean.Teacher;
import com.haijiao.Domain.service.IUserService;
import java.util.List;

public class UserServiceImpl implements IUserService{

    @Override
    public boolean confirmExist(String email) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String confirmLogin(String email, String password) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean register(String account, String password, String userType) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Teacher> searchTeacher(List<String> strList) {
        throw new UnsupportedOperationException("Not supported yet.");
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

}
