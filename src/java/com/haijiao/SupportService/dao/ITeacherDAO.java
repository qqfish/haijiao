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
public interface ITeacherDAO extends GenericDAO<Teacher, Integer>{
    public boolean confirm(String username,String password);
}
