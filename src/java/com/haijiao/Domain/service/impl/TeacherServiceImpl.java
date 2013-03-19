/**
 *
 * @author Jerry Zou
 */

package com.haijiao.Domain.service.impl;
import com.haijiao.Domain.bean.Clazz;
import com.haijiao.Domain.bean.Schedule;
import com.haijiao.Domain.bean.Teacher;
import com.haijiao.Domain.bean.Timeslice;
import com.haijiao.Domain.bean.User;
import com.haijiao.Domain.service.ITeacherService;
import java.sql.Time;
import java.util.List;

public class TeacherServiceImpl extends UserServiceImpl implements ITeacherService{

    @Override
    public Teacher getTeacherByAccount(String account) {
        Teacher tea = new Teacher();
        tea.setName("呵呵老师");
        return tea;
    }

    @Override
    public boolean comment(User commenter, User commentee, String content, Integer score) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public boolean changeSchedule(String username, Schedule s) {
        return false;
    }

    @Override
    public boolean changeInfo(String username, Teacher tc) {
        return false;
    }

    @Override
    public boolean changeAudition(String username) {
        return false;
    }

    @Override
    public boolean takeMoney(String username, int numberOfCoin) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean dealWithReservation(String username, Clazz c, boolean accept) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
   
}
