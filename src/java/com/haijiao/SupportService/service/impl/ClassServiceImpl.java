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
        c.setAccept(accept);
        clazzDAO.update(c);
        return true;
    }

    @Override
    public boolean bookStableTeacher(String teacherEmail, String studentEmail, String lesson, Integer day, Integer index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean bookTmpTeacher(String teacherEmail, String studentEmail, String lesson, int day, int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean studentPauseBook(String teacherEmail, String studentEmail, int day, int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean teacherPauseBook(String teacherEmail, String studentEmail, int day, int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean teacherAddClazz(String teacherEmail, int day, int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean teacherRemoveClazz(String teacherEmail, int day, int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean cancelBook(String teacherEmail, String studentEmail, int day, int index) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Clazz> getStudentClazz(String studentEmail) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
