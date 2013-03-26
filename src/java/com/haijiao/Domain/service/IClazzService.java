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
public interface IClazzService {
    //获取老师已完结课程
    public List<Clazz> getTeacherFinishedClasses(String username);
    //获取学生已完结课程
    public List<Clazz> getStudentFinishedClasses(String username);
    //预定老师,此函数接口定义不太准确，Clazz以后将改为很多属性的集合
    public boolean bookTeacher(String studentAccount, String teacherAccount, Clazz clazz);
    //处理学生预约
    public boolean dealWithReservation(String username, Clazz c, boolean accept);
}
