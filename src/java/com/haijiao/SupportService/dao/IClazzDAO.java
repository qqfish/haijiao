/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.dao;

import com.haijiao.Domain.bean.Clazz;
import java.util.List;

/**
 *
 * @author Jerry
 */
public interface IClazzDAO extends GenericDAO<Clazz,Integer>{
    public Clazz getClazz(String temail,String lesson,int day,int index);
    public List<Clazz> getStudentClazz(String studentEmail);
    public Clazz getClazz(int id);
}
