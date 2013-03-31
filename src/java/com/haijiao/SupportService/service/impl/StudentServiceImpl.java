/**
 *
 * @author Jerry Zou
 */

package com.haijiao.SupportService.service.impl;

import com.haijiao.Domain.bean.Clazz;
import com.haijiao.Domain.bean.Schedule;
import com.haijiao.Domain.bean.Student;
import com.haijiao.SupportService.service.IStudentService;
import com.haijiao.SupportService.dao.IStudentDAO;
import java.sql.Date;
import java.util.List;

public class StudentServiceImpl implements IStudentService{
    IStudentDAO studentDAO;

    public void setStudentDAO(IStudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    @Override
    public Student getStudentByEmail(String email) {
        return studentDAO.getStudentByEmail(email);
    }

    @Override
    public boolean changeInfo(String email, String password, String name, String sex, Date birthday, String grade, String school, String tel, String telType) {
        Student s = studentDAO.getStudentByEmail(email);
        s.setBirthday(birthday);
        s.setGrade(grade);
        s.setName(name);
        s.setSchool(school);
        s.setSex(sex);
        s.setTel(tel);
        s.setTelType(telType);
        studentDAO.update(s);
        return true;
    }

    @Override
    public boolean topUpMoney(String email, int numberOfCoin) {
        Student s = studentDAO.getStudentByEmail(email);
        int coin = s.getCoin();
        s.setCoin(coin + numberOfCoin);
        studentDAO.update(s);
        return true;
    }

    @Override
    public List<Clazz> getFinishedClasses(String email) {
        Student s = studentDAO.getStudentByEmail(email);
        return s.getClassList();
    }

    @Override
    public Schedule getSchedule(String email) {
        Student s = studentDAO.getStudentByEmail(email);
        return s.getSchedule();
    }

}
