/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.dao;

import com.haijiao.persist.Teacher;

/**
 *
 * @author hp
 */
public class TeacherDAO extends GenericHibernateDAO<Teacher, Integer> implements ITeacherDAO{
    @Override
    public boolean confirm(String username, String password) {
        String hql = "from teacher where username='"+ username +"' and password='"+ password +"'";
        return findByqQuery(hql)!=null;
    }
}
