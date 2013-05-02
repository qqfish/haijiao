/**
 *
 * @author Jerry Zou
 */

package com.haijiao.SupportService.service.impl;

import com.haijiao.Domain.bean.Clazz;
import com.haijiao.Domain.bean.FreeTime;
import com.haijiao.Domain.bean.Teacher;
import com.haijiao.SupportService.dao.IClazzDAO;
import com.haijiao.SupportService.dao.ITeacherDAO;
import com.haijiao.SupportService.service.ITeacherService;
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
    
    @Resource
    IClazzDAO classDAO;

    public void setTeacherDAO(ITeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    public void setClassDAO(IClazzDAO classDAO) {
        this.classDAO = classDAO;
    }

    @Override
    public Teacher getTeacherByEmail(String email) {
        return teacherDAO.getTeacherByEmail(email);
    }
    
    @Override
    public boolean changeInfo(String email, String name, String sex, Date birthday, String school, String tel, String province) {
        Teacher t = teacherDAO.getTeacherByEmail(email);
        if (birthday!=null) {
            t.setBirthday(birthday);
        } if (school!=null) {
            t.setSchool(school);
        } if (name!=null) {
            t.setName(name);
        } if (sex!=null) {
            t.setSex(sex);
        } if (tel!=null) {
            t.setTel(tel);
        } if (province!=null) {
            t.setProvince(province);
        }
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
    public List<FreeTime> getSchedule(String email) {
        Teacher t = teacherDAO.getTeacherByEmail(email);
        return t.getSchedule();
    }

    @Override
    public List<Clazz> getClasses(String email) {
        return classDAO.getTeacherClazz(email);
    }

    @Override
    public List<Clazz> getTodayClasses(String email) {
        return classDAO.getTeacherTodayClazz(email);
    }

    @Override
    public boolean addLesson(String email, String lessonName) {

        return true;
    }

}
