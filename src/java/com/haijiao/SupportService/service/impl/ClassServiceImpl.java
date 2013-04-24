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
import com.haijiao.global.scheduleLocation;
import java.util.ArrayList;
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
    public boolean bookTeacher(String teacherEmail, String studentEmail, String lesson, List<scheduleLocation> cList, int num) {
        Student s = studentDAO.getStudentByEmail(studentEmail);
        Teacher tea = teacherDAO.getTeacherByEmail(teacherEmail);
        Lesson l = tea.getLessonByName(lesson);
        if(l == null)
            return false;
        for (int i = 0; i < cList.size(); i++) {
            FreeTime ft = freeTimeDAO.getTeacherFreeTime(teacherEmail, cList.get(i).getDay(), cList.get(i).getIndex());
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
            if (nextClazz.getRemain() != 0) {
                clazzList.add(1, nextClazz);
                clazzDAO.makePersistent(nextClazz);
            }
            clazzDAO.update(clazzList.get(0));
            freeTimeDAO.update(ft);
        }
        System.out.println("fuck");
        return true;
    }

    @Override
    public boolean studentPauseBook(int clazzId, int num) {
        Clazz clazz = clazzDAO.findById(clazzId);
        FreeTime freeTime = clazz.getFreeTime();
        int pos = freeTime.getClazzList().indexOf(clazz);
        if (pos < 0 || pos >= freeTime.getClazzList().size()) {
            return false;
        }
        Clazz freeClazz = new Clazz(clazz);
        freeClazz.setRemain(num);
        freeClazz.setStatus(Clazz.Status.available);
        clazzDAO.makePersistent(freeClazz);
        freeTime.getClazzList().add(pos, freeClazz);
        for (int i = pos + 1; i < freeTime.getClazzList().size(); i++) {
            freeTime.getClazzList().get(i).addTimeToBegin(num);
            clazzDAO.update(freeTime.getClazzList().get(i));
        }
        freeTimeDAO.update(freeTime);
        return true;
    }

    @Override
    public boolean teacherPauseBook(int clazzId, int num) {
        Clazz clazz = clazzDAO.findById(clazzId);
        FreeTime freeTime = clazz.getFreeTime();
        int pos = freeTime.getClazzList().indexOf(clazz);
        if (pos < 0 || pos >= freeTime.getClazzList().size()) {
            return false;
        }
        Clazz freeClazz = new Clazz(clazz);
        freeClazz.setRemain(num);
        freeClazz.setStatus(Clazz.Status.notAvailable);
        clazzDAO.makePersistent(freeClazz);
        freeTime.getClazzList().add(pos, freeClazz);
        for (int i = pos + 1; i < freeTime.getClazzList().size(); i++) {
            freeTime.getClazzList().get(i).addTimeToBegin(num);
            clazzDAO.update(freeTime.getClazzList().get(i));
        }
        freeTimeDAO.update(freeTime);
        return true;
    }

    @Override
    public boolean teacherAddClazz(String teacherEmail, List<scheduleLocation> cList) {
        Teacher tea = teacherDAO.getTeacherByEmail(teacherEmail);
        for (int i = 0; i < cList.size(); i++) {
            Clazz freeClazz = new Clazz();
            freeClazz.setRemain(-1);
            freeClazz.setStatus(Clazz.Status.available);
            freeClazz.setTimeToBegin(0);
            clazzDAO.makePersistent(freeClazz);
            List<Clazz> clazzList = new ArrayList();
            clazzList.add(freeClazz);
            FreeTime freeTime = new FreeTime();
            freeTime.setClazzList(clazzList);
            freeTime.setWeekday(cList.get(i).getDay());
            freeTime.setSliceIndex(cList.get(i).getIndex());
            freeTime.setTeacher(tea);
            freeTimeDAO.makePersistent(freeTime);
            freeClazz.setFreeTime(freeTime);
            clazzDAO.update(freeClazz);
            tea.getSchedule().add(freeTime);
        }
        teacherDAO.update(tea);
        return true;
    }

    @Override
    public boolean teacherRemoveClazz(String teacherEmail, List<scheduleLocation> cList) {
        Teacher tea = teacherDAO.getTeacherByEmail(teacherEmail);
        for (int i = 0; i < cList.size(); i++) {
            FreeTime freeTime = tea.getFreeTime(cList.get(i).getDay(), cList.get(i).getIndex());
            if (freeTime == null) {
                return false;
            }
            tea.getSchedule().remove(freeTime);
            freeTimeDAO.makeTransient(freeTime);
        }
        teacherDAO.update(tea);
        return true;
    }

    @Override
    public boolean dealWithReservation(int clazzId, boolean accept) {
        if (!accept) {
            return cancelBook(clazzId);
        }
        Clazz clazz = clazzDAO.findById(clazzId);
        if (clazz == null) {
            return false;
        }
        clazz.setStatus(Clazz.Status.accept);
        return true;
    }

    @Override
    public boolean cancelBook(int clazzId) {
        Clazz clazz = clazzDAO.findById(clazzId);
        int pos = clazz.getFreeTime().getClazzList().indexOf(clazz);
        if (pos < 0 || pos >= clazz.getFreeTime().getClazzList().size()) {
            return false;
        }
        for (int i = pos + 1; i < clazz.getFreeTime().getClazzList().size(); i++) {
            if (clazz.getRemain() != -1) {
                clazz.getFreeTime().getClazzList().get(i).addTimeToBegin(-clazz.getRemain());
                clazzDAO.update(clazz.getFreeTime().getClazzList().get(i));
            }
        }
        clazz.getFreeTime().getClazzList().remove(pos);
        clazzDAO.makeTransient(clazz);
        freeTimeDAO.update(clazz.getFreeTime());
        return true;
    }
    
    @Override
    public Clazz getClazzById(int clazzId) {
        return clazzDAO.findById(clazzId);
    }
}
