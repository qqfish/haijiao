/**
 *
 * @author Jerry Zou
 */

package com.haijiao.SupportService.dao.impl;
import com.haijiao.Domain.bean.User;
import com.haijiao.SupportService.dao.GenericHibernateDAO;
import com.haijiao.SupportService.dao.IUserDAO;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl extends GenericHibernateDAO<User, Integer> implements IUserDAO {

    @Override
    public boolean confirmExist(String account) {
        String hql = "from User where email='"+ account + "'";
        return (!findByQuery(hql).isEmpty());
    }
    
    @Override
    public User getUserByEmail(String email) {
        String hql = "from User where email='" + email + "'";
        List<User> lu = findByQuery(hql);
        if(lu.isEmpty())
            return null;
        else
            return lu.get(0);
    }
    
    @Override
    public String confirmLogin(String account, String password) {
        String hql = "from User where email='"+ account +"' and password='"+ password +"'";
        List<User> ul= findByQuery(hql);
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
