/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.dao;

import com.haijiao.persist.Student;

/**
 *
 * @author hp
 */
public class StudentDAO extends GenericHibernateDAO<Student, Integer> implements IStudentDAO{
    @Override
    public boolean confirm(String username,String password){
        String hql = "from student where username='"+ username +"' and password='"+ password +"'";
        return findByqQuery(hql)!=null;
    }
}