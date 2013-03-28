/**
 *
 * @author Jerry Zou
 */

package com.haijiao.Domain.service.impl;

import com.haijiao.Domain.bean.Clazz;
import com.haijiao.Domain.bean.Schedule;
import com.haijiao.Domain.bean.Student;
import com.haijiao.Domain.service.IStudentService;
import java.sql.Date;
import java.util.List;

public class StudentServiceImpl implements IStudentService{

    @Override
    public Student getStudentByEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean changeInfo(String email, String password, String name, String sex, Date birthday, String grade, String school, String tel, String telType) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean topUpMoney(String email, int numberOfCoin) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Clazz> getFinishedClasses(String email) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Schedule getSchedule(String email) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean bookTeacher(String teacherEmail, String studentEmail, String lesson, Date date, Integer week, String day, Integer beginIndex, Integer endIndex) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
