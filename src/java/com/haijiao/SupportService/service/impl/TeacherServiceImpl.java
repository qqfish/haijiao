/**
 *
 * @author Jerry Zou
 */

package com.haijiao.SupportService.service.impl;

import com.haijiao.Domain.bean.Clazz;
import com.haijiao.Domain.bean.FreeTime;
import com.haijiao.Domain.bean.Lesson;
import com.haijiao.Domain.bean.Teacher;
import com.haijiao.SupportService.dao.IClazzDAO;
import com.haijiao.SupportService.dao.ILessonDAO;
import com.haijiao.SupportService.dao.ITeacherDAO;
import com.haijiao.SupportService.service.ITeacherService;
import java.sql.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TeacherServiceImpl implements ITeacherService {
    @Resource
    ITeacherDAO teacherDAO;
    
    @Resource
    IClazzDAO classDAO;
    
    @Resource
    ILessonDAO lessonDAO;

    public void setTeacherDAO(ITeacherDAO teacherDAO) {
        this.teacherDAO = teacherDAO;
    }

    public void setClassDAO(IClazzDAO classDAO) {
        this.classDAO = classDAO;
    }

    public void setLessonDAO(ILessonDAO lessonDAO) {
        this.lessonDAO = lessonDAO;
    }

    @Override
    @Transactional(propagation=Propagation.SUPPORTS)
    public Teacher getTeacherByEmail(String email) {
        return teacherDAO.getTeacherByEmail(email);
    }
    
    @Override
    public boolean changeInfo(String email, String name, String sex, Date birthday, String school, String tel, String province, String net) {
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
        } if (net!=null) {
            t.setNet(net);
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
    @Transactional(propagation=Propagation.SUPPORTS)
    public List<FreeTime> getSchedule(String email) {
        Teacher t = teacherDAO.getTeacherByEmail(email);
        return t.getSchedule();
    }

    @Override
    @Transactional(propagation=Propagation.SUPPORTS)
    public List<Clazz> getClasses(String email) {
        return classDAO.getTeacherClazz(email);
    }

    @Override
    @Transactional(propagation=Propagation.SUPPORTS)
    public List<Clazz> getTodayClasses(String email) {
        return classDAO.getTeacherTodayClazz(email);
    }

    @Override
    public boolean addLesson(String email, String lessonName) {
        Teacher t = teacherDAO.getTeacherByEmail(email);
        Lesson l = lessonDAO.getLessonByName(email,lessonName);
        if(l == null || l.isDelete()){
            if(l != null){
                l.setDelete(false);
                lessonDAO.update(l);
            } else {
                l = new Lesson();
                l.setName(lessonName);
                lessonDAO.makePersistent(l);
            }
            List ll = t.getLessons();
            ll.add(l);
            t.setLessons(ll);
            teacherDAO.update(t);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteLesson(String email, String lessonName) {
        Teacher t = teacherDAO.getTeacherByEmail(email);
        Lesson l = lessonDAO.getLessonByName(email,lessonName);
        if(l != null){
            l.setDelete(true);
            lessonDAO.update(l);
        }
        return true;
    }

}
