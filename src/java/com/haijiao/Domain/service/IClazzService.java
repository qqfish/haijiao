/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.Domain.service;

import com.haijiao.Domain.bean.Clazz;
import java.util.List;

/**
 *
 * @author Jerry
 */
public interface IClazzService extends Generic<Clazz,Integer>{
    //预定老师
    public boolean bookTeacher(Clazz clazz);
    //处理学生预约
    public boolean dealWithReservation(int id, boolean accept);
}
