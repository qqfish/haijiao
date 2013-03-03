/**
 *
 * @author Jerry Zou
 */

package com.haijiao.Domain.service.impl;
import com.haijiao.Domain.bean.Schedule;
import com.haijiao.Domain.bean.Teacher;
import com.haijiao.Domain.bean.Timeslice;
import com.haijiao.Domain.bean.User;
import com.haijiao.Domain.service.ITeacherService;

public class TeacherServiceImpl extends UserServiceImpl implements ITeacherService{

    @Override
    public Teacher getTeacherByAccount(String account) {
        Teacher tea = new Teacher();
        tea.setAccount(account);
        tea.setBrief_intro("很高兴和同学们交朋友");
        tea.setWagePerhour(35);
        tea.setAudition(false);
        tea.setComments(null);
        tea.setClasses(null);
        tea.setMoney(2000);
        tea.setVideoURL(null);
        tea.setSchedule(null);
        return tea;
    }

    @Override
    public boolean comment(User commenter, User commentee, String content, Integer score) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public boolean changeSchedule(Schedule s) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean changeInfo(Teacher tc) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean changeAudition(Teacher tc, boolean boo) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean takeMoney(Teacher tc, int number) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean dealToReservation(Teacher tc, Timeslice ts) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
   
}
