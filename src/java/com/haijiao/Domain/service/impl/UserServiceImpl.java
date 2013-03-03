/**
 *
 * @author Jerry Zou
 */

package com.haijiao.Domain.service.impl;
import com.haijiao.Domain.bean.User;
import com.haijiao.Domain.service.IUserService;

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

}
