/**
 *
 * @author Jerry Zou
 */

package com.haijiao.Domain.service.impl;

import com.haijiao.Domain.bean.Clazz;
import com.haijiao.Domain.bean.Schedule;
import com.haijiao.Domain.bean.Student;
import com.haijiao.Domain.service.IStudentService;
import com.haijiao.SupportService.dao.IClazzDAO;
import com.haijiao.SupportService.dao.IStudentDAO;
import java.sql.Date;
import java.util.List;

public class StudentServiceImpl implements IStudentService{
    IStudentDAO studentDAO;
    IClazzDAO clazzDAO;

    @Override
    public Student getStudentByEmail(String email) {
        return studentDAO.getStudentByEmail(email);
    }

    @Override
    public boolean changeInfo(String email, String password, String name, String sex, Date birthday, String grade, String school, String tel, String telType) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean topUpMoney(String email, int numberOfCoin) {
        return studentDAO.topUpMoney(email, numberOfCoin);
    }

    @Override
    public List<Clazz> getFinishedClasses(String email) {
        return studentDAO.getFinishedClasses(email);
    }

    @Override
    public Schedule getSchedule(String email) {
        return studentDAO.getSchedule(email);
    }

    @Override
    public boolean bookTeacher(String teacherEmail, String studentEmail, String lesson, Date date, Integer week, String day, Integer beginIndex, Integer endIndex) {
        return clazzDAO.bookTeacher(teacherEmail, studentEmail, lesson, date, week, day, beginIndex, endIndex);
    }

    public IStudentDAO getStudentDAO() {
        return studentDAO;
    }

    public void setStudentDAO(IStudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public IClazzDAO getClazzDAO() {
        return clazzDAO;
    }

    public void setClazzDAO(IClazzDAO clazzDAO) {
        this.clazzDAO = clazzDAO;
    }

}
