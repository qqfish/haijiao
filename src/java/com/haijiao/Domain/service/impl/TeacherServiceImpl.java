/**
 *
 * @author Jerry Zou
 */

package com.haijiao.Domain.service.impl;

import com.haijiao.Domain.bean.Clazz;
import com.haijiao.Domain.bean.Schedule;
import com.haijiao.Domain.bean.Teacher;
import com.haijiao.Domain.service.ITeacherService;
import com.haijiao.SupportService.dao.IClazzDAO;
import com.haijiao.SupportService.dao.ITeacherDAO;
import java.sql.Date;
import java.util.List;

public class TeacherServiceImpl implements ITeacherService {
    ITeacherDAO teacherDAO;
    IClazzDAO clazzDAO;

    @Override
    public Teacher getTeacherByEmail(String email) {
        return teacherDAO.getTeacherByEmail(email);
    }

    @Override
    public boolean changeInfo(String email, String password, String name, String sex, Date birthday, String school, String tel) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean takeMoney(String email, int numberOfCoin) {
        return teacherDAO.takeMoney(email, numberOfCoin);
    }

    @Override
    public boolean changeAudition(String email) {
        return teacherDAO.changeAudition(email);
    }

    @Override
    public List<Clazz> getFinishedClasses(String email) {
        return teacherDAO.getFinishedClasses(email);
    }

    @Override
    public Schedule getSchedule(String email) {
        return teacherDAO.getSchedule(email);
    }

    @Override
    public boolean changeSchedule(String email, Schedule s) {
        return teacherDAO.changeSchedule(email, s);
    }

    @Override
    public boolean dealWithReservation(int clazzId, boolean accept) {
        return clazzDAO.dealWithReservation(clazzId, accept);
    }

    public ITeacherDAO getTeacherDAO() {
        return teacherDAO;
    }

    public void setTeacherDAO(ITeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    public IClazzDAO getClazzDAO() {
        return clazzDAO;
    }

    public void setClazzDAO(IClazzDAO clazzDAO) {
        this.clazzDAO = clazzDAO;
    }

}
