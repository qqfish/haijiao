/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.dao.impl;

import com.haijiao.SupportService.dao.GenericHibernateDAO;
import com.haijiao.SupportService.dao.IStudentDAO;
import com.haijiao.persist.Student;

/**
 *
 * @author hp
 */
public class StudentDAOImpl extends GenericHibernateDAO<Student, Integer> implements IStudentDAO{
    @Override
    public boolean confirm(String username,String password){
        String hql = "from student where acccount='"+ username +"' and password='"+ password +"'";
        return findByqQuery(hql)!=null;
    }
}
