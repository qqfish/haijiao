/**
 *
 * @author Jerry Zou
 */

package com.haijiao.SupportService.service.impl;

import com.haijiao.Domain.bean.Clazz;
import com.haijiao.Domain.bean.Schedule;
import com.haijiao.Domain.bean.Teacher;
import com.haijiao.SupportService.service.ITeacherService;
import com.haijiao.SupportService.dao.ITeacherDAO;
import java.sql.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TeacherServiceImpl implements ITeacherService {
    @Resource
    ITeacherDAO teacherDAO;

    public void setTeacherDAO(ITeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    @Override
    public Teacher getTeacherByEmail(String email) {
        return teacherDAO.getTeacherByEmail(email);
    }

    @Override
    public boolean changeInfo(String email, String password, String name, String sex, Date birthday, String school, String tel) {
        Teacher t = teacherDAO.getTeacherByEmail(email);
        t.setBirthday(birthday);
        t.setName(name);
        t.setPassword(password);
        t.setSchool(school);
        t.setSex(sex);
        t.setTel(tel);
        teacherDAO.update(t);
        return true;
    }

    @Override
    public boolean takeMoney(String email, int numberOfCoin) {
        Teacher t = teacherDAO.getTeacherByEmail(email);
        int coin = t.getCoin();
        t.setCoin(coin - numberOfCoin);
        teacherDAO.update(t);
        return true;
    }

    @Override
    public boolean changeAudition(String email) {
        Teacher t = teacherDAO.getTeacherByEmail(email);
        boolean audition = t.isAudition();
        t.setAudition(!audition);
        teacherDAO.update(t);
        return true;
    }

    @Override
    public List<Clazz> getFinishedClasses(String email) {
        Teacher t = teacherDAO.getTeacherByEmail(email);
        return t.getClasslist();
    }

    @Override
    public Schedule getSchedule(String email) {
        Teacher t = teacherDAO.getTeacherByEmail(email);
        return t.getSchedule();
    }

    @Override
    public boolean changeSchedule(String email, Schedule s) {
        Teacher t = teacherDAO.getTeacherByEmail(email);
        t.setSchedule(s);
        teacherDAO.update(t);
        return true;
    }

}
