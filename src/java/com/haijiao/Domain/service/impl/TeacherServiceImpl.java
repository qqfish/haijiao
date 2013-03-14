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
        tea.setAccount(account);
        tea.setBrief_intro("很高兴和同学们交朋友");
        tea.setWagePerhour(35);
        tea.setAudition(false);
        tea.setComments(null);
        tea.setClasses(null);
        tea.setCoin(2000);
        tea.setVideoUrl(null);
        Schedule scd = new Schedule();
        Timeslice s = new Timeslice();
        s.setDay(1);
        s.setBegintime(Time.valueOf("08:00:00"));
        s.setEndtime(Time.valueOf("12:00:00"));
        s.setType("free");
        scd.getSlices().add(s);
        s = new Timeslice();
        s.setDay(3);
        s.setBegintime(Time.valueOf("15:00:00"));
        s.setEndtime(Time.valueOf("16:00:00"));
        s.setType("class");
        scd.getSlices().add(s);
        s = new Timeslice();
        s.setDay(5);
        s.setBegintime(Time.valueOf("21:00:00"));
        s.setEndtime(Time.valueOf("23:00:00"));
        s.setType("free");
        scd.getSlices().add(s);
        tea.setSchedule(scd);
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
