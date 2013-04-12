/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.service.impl;

import com.haijiao.Domain.bean.Clazz;
import com.haijiao.Domain.bean.FreeTime;
import com.haijiao.Domain.bean.Lesson;
import com.haijiao.Domain.bean.Student;
import com.haijiao.Domain.bean.Teacher;
import com.haijiao.SupportService.service.IClassService;
import com.haijiao.SupportService.dao.IClazzDAO;
import com.haijiao.SupportService.dao.IFreeTimeDAO;
import com.haijiao.SupportService.dao.IStudentDAO;
import com.haijiao.SupportService.dao.ITeacherDAO;
import java.util.List;
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
    
    @Resource
    IFreeTimeDAO freeTimeDAO;

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
    public boolean dealWithReservation(int clazzId, boolean accept) {
        Clazz c = clazzDAO.findById(clazzId);
        if(accept){
            c.setStatus(Clazz.Status.accept);
            clazzDAO.update(c);
            return true;
        }
        else{
            clazzDAO.makeTransient(c);
            return true;
        }
    }

    @Override
    public boolean bookStableTeacher(String teacherEmail, String studentEmail, String lesson, Integer day, Integer index) {
        Teacher t = teacherDAO.getTeacherByEmail(teacherEmail);
        Student s = studentDAO.getStudentByEmail(studentEmail);
        Clazz c = new Clazz();
        Lesson l = new Lesson();
        l.setName(lesson);
        c.setDay(day);
        c.setIndex(index);
        c.setLesson(l);
        c.setRemain(-1);
        c.setStatus(Clazz.Status.available);
        c.setStudent(s);
        c.setTeacher(t);
        return clazzDAO.makePersistent(c);
    }

    @Override
    public boolean bookTmpTeacher(String teacherEmail, String studentEmail, String lesson, int day, int index) {
        Teacher t = teacherDAO.getTeacherByEmail(teacherEmail);
        Student s = studentDAO.getStudentByEmail(studentEmail);
        Clazz c = new Clazz();
        Lesson l = new Lesson();
        l.setName(lesson);
        c.setDay(day);
        c.setIndex(index);
        c.setLesson(l);
        c.setRemain(1);
        c.setStatus(1);
        c.setStudent(s);
        c.setTeacher(t);
        return clazzDAO.makePersistent(c);
    }

    @Override
    public boolean studentPauseBook(String teacherEmail, String studentEmail, int day, int index) {
        Clazz c = clazzDAO.getClazz(studentEmail, studentEmail, day, index);
        int timeToBegin = c.getTimeToBegin();
        c.setTimeToBegin(timeToBegin + 1);
        c.setStatus(1);
        clazzDAO.update(c);
        return true;
    }

    @Override
    public boolean teacherPauseBook(String teacherEmail, String studentEmail, int day, int index) {
        Clazz c = clazzDAO.getClazz(studentEmail, studentEmail, day, index);
        int timeToBegin = c.getTimeToBegin();
        c.setTimeToBegin(timeToBegin + 1);
        c.setStatus(0);
        clazzDAO.update(c);
        return true;
    }

    @Override
    public boolean teacherAddClazz(String teacherEmail, int day, int index) {
        FreeTime ft = freeTimeDAO.getTeacherFreeTime(teacherEmail, day, index);
        return true;
    }

    @Override
    public boolean teacherRemoveClazz(String teacherEmail, int day, int index) {
        FreeTime ft = freeTimeDAO.getTeacherFreeTime(teacherEmail, day, index);
        freeTimeDAO.update(ft);
        return true;
    }

    @Override
    public boolean cancelBook(String teacherEmail, String studentEmail, int day, int index) {
        Clazz c = clazzDAO.getClazz(studentEmail, studentEmail, day, index);
        return clazzDAO.makeTransient(c);
    }

    @Override
    public List<Clazz> getStudentClazz(String studentEmail) {
        return clazzDAO.getStudentClazz(studentEmail);
    }
}
