/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.service.impl;

import com.haijiao.Domain.bean.Clazz;
import com.haijiao.Domain.bean.Lesson;
import com.haijiao.Domain.bean.Student;
import com.haijiao.Domain.bean.Teacher;
import com.haijiao.SupportService.service.IClassService;
import com.haijiao.SupportService.dao.IClazzDAO;
import com.haijiao.SupportService.dao.IStudentDAO;
import com.haijiao.SupportService.dao.ITeacherDAO;
import java.sql.Date;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author hp
 */

@Service
@Transactional
public class ClassServiceImpl implements IClassService{
    @Resource
    IClazzDAO clazzDAO;
    
    @Resource
    ITeacherDAO teacherDAO;
    
    @Resource
    IStudentDAO studentDAO;

    public void setClazzDAO(IClazzDAO clazzDAO) {
        this.clazzDAO = clazzDAO;
    }

    public void setTeacherDAO(ITeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    public void setStudentDAO(IStudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }
    
    @Override
    public boolean bookTeacher(String teacherEmail, String studentEmail, String lesson, Date date, Integer week, String day, Integer beginIndex, Integer endIndex) {
        Clazz c = new Clazz();
        Teacher t = teacherDAO.getTeacherByEmail(teacherEmail);
        Student s = studentDAO.getStudentByEmail(studentEmail);
        Lesson l = new Lesson();
        l.setName(lesson);
        c.setBeginIndex(beginIndex);
        c.setDate(date);
        c.setDay(day);
        c.setEndIndex(endIndex);
        c.setFinish(false);
        c.setLesson(l);
        c.setWeek(week);
        c.setStudent(s);
        c.setTeacher(t);
        return clazzDAO.makePersistent(c);
    }

    @Override
    public boolean dealWithReservation(int clazzId, boolean accept) {
        Clazz c = clazzDAO.findById(clazzId);
        c.setAccept(accept);
        clazzDAO.update(c);
        return true;
    }
}
