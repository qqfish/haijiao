/**
 *
 * @author Jerry Zou
 */

package com.haijiao.Domain.service.impl;
import com.haijiao.Domain.bean.Teacher;
import com.haijiao.Domain.bean.User;
import com.haijiao.Domain.service.IUserService;
import java.util.List;

public class UserServiceImpl implements IUserService {

    @Override
    public boolean confirmRegister(String account, String password, String userType) {
        return true;
    }
    
    @Override
    public String confirmLogin(String account, String password) {
        if(account.equalsIgnoreCase("TEST") && password.equals("123456")){
            return "老师";
        }
        else {
            return null;
        }
    }

    @Override
    public boolean comment(User commenter, User commentee, String content, Integer score) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Teacher> searchTeacher(String name, Integer grade, String subject, String net) {
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
