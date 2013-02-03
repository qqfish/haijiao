/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.dao.impl;

import com.haijiao.dao.GenericHibernateDAO;
import com.haijiao.dao.ITeacherDAO;
import com.haijiao.persist.Teacher;

/**
 *
 * @author hp
 */
public class TeacherDAOImpl extends GenericHibernateDAO<Teacher, Integer> implements ITeacherDAO{
    @Override
    public boolean confirm(String username, String password) {
        String hql = "from teacher where account='"+ username +"' and password='"+ password +"'";
        return findByqQuery(hql)!=null;
    }
}
