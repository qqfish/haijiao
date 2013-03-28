/**
 *
 * @author Jerry Zou
 */

package com.haijiao.Domain.service.impl;

import com.haijiao.Domain.bean.Clazz;
import com.haijiao.Domain.bean.Schedule;
import com.haijiao.Domain.bean.Teacher;
import com.haijiao.Domain.service.ITeacherService;
import java.sql.Date;
import java.util.List;

public class TeacherServiceImpl implements ITeacherService {

    @Override
    public Teacher getTeacherByEmail(String email) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean changeInfo(String email, String password, String name, String sex, Date birthday, String school, String tel) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean takeMoney(String email, int numberOfCoin) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean changeAudition(String email) {
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
    public boolean changeSchedule(String email, Schedule s) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean dealWithReservation(int clazzId, boolean accept) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
