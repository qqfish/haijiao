/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.dao;

import com.haijiao.persist.Student;

/**
 *
 * @author hp
 */
public interface IStudentDAO extends GenericDAO<Student, Integer>{
    public boolean confirm(String username,String password);
}
