/**
 *
 * @author Jerry Zou
 */

package com.haijiao.Domain.service.impl;
import com.haijiao.Domain.bean.User;
import com.haijiao.Domain.service.GenericService;
import com.haijiao.Domain.service.IUserService;
import java.util.List;

public class UserServiceImpl extends GenericService<User, Integer> implements IUserService {

    @Override
    public boolean confirmExist(String account) {
        String hql = "from User where email='"+ account + "'";
        return findByqQuery(hql)==null;
    }
    
    @Override
    public String confirmLogin(String account, String password) {
        String hql = "from User where email='"+ account +"' and password='"+ password +"'";
        List<User> ul= findByqQuery(hql);
        if(ul.isEmpty()){
            return null;
        }
        else{
            String usertype = ul.get(0).getUserType();
            return usertype;
        }
    }

    @Override
    public boolean uploadFile(String account, Object file) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object download(String fileuri) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
