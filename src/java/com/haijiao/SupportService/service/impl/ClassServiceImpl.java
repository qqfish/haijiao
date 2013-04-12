/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.haijiao.SupportService.service.impl;

import com.haijiao.Domain.bean.Clazz;
import com.haijiao.Domain.bean.FreeTime;
import com.haijiao.Domain.bean.Lesson;
import com.haijiao.Domain.bean.Student;
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
public class ClassServiceImpl implements IClassService {

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
    public boolean bookTeacher(String teacherEmail, String studentEmail, String lesson, Integer day, Integer index, int num) {
        Student s = studentDAO.getStudentByEmail(studentEmail);
        Lesson l = new Lesson();
        l.setName(lesson);
        FreeTime ft = freeTimeDAO.getTeacherFreeTime(teacherEmail, day, index);
        List<Clazz> clazzList = ft.getClazzList();
        Clazz nextClazz = new Clazz(clazzList.get(0));
        if (nextClazz.getStatus() != Clazz.Status.available) {
            return false;
        }
        if (nextClazz.getRemain() > 0) {
            if (nextClazz.getRemain() < num) {
                return false;
            }
            nextClazz.setRemain(nextClazz.getRemain() - num);
        }
        clazzList.get(0).setLesson(l);
        clazzList.get(0).setRemain(num);
        clazzList.get(0).setStatus(Clazz.Status.notAccept);
        clazzList.get(0).setStudent(s);
        clazzList.get(0).setTimeToBegin(0);
        nextClazz.setTimeToBegin(num);
        if(nextClazz.getRemain() > 0){
            clazzList.add(1, nextClazz);
            clazzDAO.makePersistent(nextClazz);
        }
        freeTimeDAO.update(ft);
        return true;
    }

    @Override
    public boolean studentPauseBook(int clazzId, int num) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean teacherPauseBook(int clazzId, int num) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean teacherAddClazz(String teacherEmail, int day, int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean teacherRemoveClazz(String teacherEmail, int day, int index) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean dealWithReservation(int clazzId, boolean accept) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean cancelBook(int clazzId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Clazz> getStudentClazz(String studentEmail) {
        return clazzDAO.getStudentClazz(studentEmail);
    }
}
